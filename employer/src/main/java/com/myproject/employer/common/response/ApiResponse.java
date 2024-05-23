package com.myproject.employer.common.response;

import ch.qos.logback.core.spi.ErrorCodes;
import com.myproject.employer.common.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {

    private Integer errorCode;  // ma loi quy dinh
    private Integer statusCode; // http status code
    private String message; // message mo ta loi
    private T object;
public static <T> ApiResponse<T> success (T object) {

    return ApiResponse.<T>builder()
            .errorCode(ErrorCode.SUCCESS)
            .statusCode(HttpStatus.OK.value())
            .object(object)
            .build();
}
public static <T> ApiResponse<T> error (Integer errorCode, HttpStatus httpStatus, String message) {
    return ApiResponse.<T>builder()
            .errorCode(errorCode)
            .statusCode(httpStatus.value())
            .message(message)
            .build();
}


}


