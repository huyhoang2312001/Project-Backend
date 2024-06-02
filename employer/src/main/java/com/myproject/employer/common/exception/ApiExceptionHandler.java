package com.myproject.employer.common.exception;
import com.myproject.employer.common.errorcode.ErrorCode;
import com.myproject.employer.common.response.ApiResponse;
import io.sentry.Sentry;
import io.sentry.SentryEvent;

import io.sentry.SentryLevel;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeType;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        captureException(e, e.getHttpStatus());
        return responseEntity(e.getErrorCode(), e.getHttpStatus(), e.getMessage());

    }
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String supportedMethods = ex.getSupportedMethods() == null ? null : String.join(",", ex.getSupportedMethods());
        String msg = String.format("Method not supported: %s, only support %s", ex.getMethod(), supportedMethods);
        captureException(ex, status);
        return responseEntity(ErrorCode.METHOD_NOT_ALLOWED, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported
            (HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        String supportedContentTypes = ex.getSupportedMediaTypes().stream().map(MimeType::toString).collect(Collectors.joining(", "));
        String msg = String.format("MediaType not supported: %s, only support %s", ex.getContentType(), supportedContentTypes);
        captureException(ex, status);
        return responseEntity(ErrorCode.UNSUPPORTED_MEDIA_TYPE, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable
            (HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String supportedContentTypes = ex.getSupportedMediaTypes().stream().map(MimeType::toString).collect(Collectors.joining(", "));
        String msg = String.format("MediaType not acceptable: only support %s", supportedContentTypes);
        captureException(ex, status);
        return responseEntity(ErrorCode.NOT_ACCEPTABLE, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable
            (MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();
        String msg = String.format("MissingPathVariable: variable name %s, parameter %s", ex.getVariableName(), ex.getParameter().getParameterName());
        captureException(ex, status);
        return responseEntity(ErrorCode.INTERNAL_ERR, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String fieldErrors = ex.getFieldErrors().stream().map(
                fieldError -> String.format("%s:%s", fieldError.getObjectName(), fieldError.getField()))
                .collect(Collectors.joining(", "));
        String glObjectErrors = ex.getGlobalErrors().stream().map(ObjectError::getObjectName).collect(Collectors.joining(", "));
                String msg = String.format("MethodArgumentNotValid field errors: %s, global errors: %s",fieldErrors);
                captureException(ex, status);
                return responseEntity(ErrorCode.BAD_REQUEST, status, msg);

    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException
            (NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = String.format("NoHandlerFound: method %s, url %s", ex.getHttpMethod(), ex.getRequestURL());
        captureException(ex, status);
        return responseEntity(ErrorCode.NOT_FOUND, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException
            (AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();
        captureException(ex, status);
        return responseEntity(ErrorCode.SERVICE_UNAVAILABLE, status, "AsyncRequestTimeout");
    }
    @Override
    protected ResponseEntity<Object> handleErrorResponseException
            (ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        captureException(ex, status);
        return responseEntity(status.value(), status, ex.getDetailMessageCode());
    }
    @Override
    protected ResponseEntity<Object>  handleConversionNotSupported
            (ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();
        String requiredType = ex.getRequiredType() == null ? null : ex.getRequiredType().getSimpleName();
        String msg = String.format("ConversionNotSupported: required type %s", requiredType);
        captureException(ex, status);
        return responseEntity(ErrorCode.INTERNAL_ERR, status, msg);
    }
    @Override
    protected ResponseEntity<Object>  handleTypeMismatch
            (TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String requiredType = ex.getRequiredType() == null ? null : ex.getRequiredType().getSimpleName();
        String msg = String.format("ConversionNotSupported: property %s, required type %s", ex.getRequiredType(), requiredType);
        captureException(ex, status);
        return responseEntity(ErrorCode.BAD_REQUEST, status, msg);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        captureException(ex, status);
        return responseEntity(ErrorCode.BAD_REQUEST, status, "HttpMessageNotReadable");
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable
            (HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();
        captureException(ex, status);
        return responseEntity(ErrorCode.INTERNAL_ERR, status, "HttpMessageNotWritable");
    }


    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> handleAuthException(AuthenticationException e) {
        e.printStackTrace();
        captureException(e, HttpStatus.UNAUTHORIZED);
        return responseEntity(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleUnknownException(Exception e) {
        e.printStackTrace();
        captureException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity(ErrorCode.INTERNAL_ERR, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
    private ResponseEntity<Object> responseEntity(Integer errorCode, HttpStatusCode statusCode, String msg) {
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .errorCode(errorCode)
                        .statusCode(statusCode.value())
                        .message(msg)
                        .build(), statusCode
        );
    }
    private void captureException(Exception e, HttpStatusCode status) {

        SentryEvent event = new SentryEvent(e);
        if (status.is5xxServerError()) {
            event.setLevel(SentryLevel.ERROR);
        } else {
            event.setLevel(SentryLevel.INFO);
        }
        Sentry.captureEvent(event);
    }
}
