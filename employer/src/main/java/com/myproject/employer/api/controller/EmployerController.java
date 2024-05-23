package com.myproject.employer.api.controller;

import com.myproject.employer.api.dto.in.EmployerDtoIn;
import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.UpdateEmployerDtoIn;
import com.myproject.employer.api.dto.out.EmployerDtoOut;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.entity.jpa.Employers;
import com.myproject.employer.api.service.EmployerService;
import com.myproject.employer.common.controller.AbstractResponseController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


import java.awt.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/employer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employer", description = "Quản lý Employer")
public class EmployerController extends AbstractResponseController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @Operation(
            summary = "Thêm mới employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseEmployer.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: email đã tồn tại hoặc provinceId không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình thêm mới employer"
                    )
            }
    )
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid EmployerDtoIn employerDtoIn){

        return responseEntity(() -> {
            return this.employerService.create(employerDtoIn);
        }, HttpStatus.CREATED);
}

    @Operation(
            summary = "Chỉnh sửa employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseEmployer.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: id không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình thêm mới employer"
                    )
            }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id")Long id,@RequestBody  @Valid  UpdateEmployerDtoIn updateEmployerDtoIn){
        return responseEntity(() -> {
            return this.employerService.update(id, updateEmployerDtoIn);
        } );


    }

    @Operation(
            summary = "Lấy thông tin 1 employer theo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseEmployer.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: id không tồn tại"
            ),

            }
    )
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.employerService.get(id);
        });
    }


    @Operation(
            summary = "Lấy danh sách employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ResponsePageEmployer.class
                                    )
                            )
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: email đã tồn tại hoặc provinceId không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình lấy danhh sách employer"
                    )
            }
    )
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list(@RequestBody @Valid PageDtoIn pageDtoIn) {
        return responseEntity(() -> {
            return this.employerService.list(pageDtoIn);
        });
    }
    @Operation(
            summary = "Xóa employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = com.myproject.employer.common.response.ApiResponse.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: id không tồn tại"
            ),

            }
    )
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            this.employerService.delete(id);
            return new HashMap<>();
        });
    }

private static class ResponseEmployer extends com.myproject.employer.common.response.ApiResponse<EmployerDtoOut>{

}
private static class ResponsePageEmployer extends com.myproject.employer.common.response.ApiResponse<PageDtoOut<EmployerDtoOut>>{

}

}




