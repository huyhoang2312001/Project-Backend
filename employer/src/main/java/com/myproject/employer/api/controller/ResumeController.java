package com.myproject.employer.api.controller;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.resume.ResumeDtoIn;
import com.myproject.employer.api.dto.in.resume.UpdateResumeDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.resume.ResumeDtoOut;
import com.myproject.employer.api.service.resume.ResumeService;
import com.myproject.employer.common.controller.AbstractResponseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/resume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Resume", description = "Quản lý resume")
public class ResumeController extends AbstractResponseController {
    private final ResumeService resumeService;
    @Autowired

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }
    @Operation(
            summary = "Thêm mới employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResumeController.ResponseResume.class
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
    public ResponseEntity<?> create(@RequestBody @Valid ResumeDtoIn resumeDtoIn) {
        return responseEntity(() -> {
            return this.resumeService.create(resumeDtoIn);
        });
    }
    @Operation(
            summary = "Chỉnh sửa employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResumeController.ResponseResume.class
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
    @PutMapping(value = "{/id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, UpdateResumeDtoIn updateResumeDtoIn) {
        return responseEntity(() -> {
            return  this.resumeService.update(id, updateResumeDtoIn);
        });
    }
    @Operation(
            summary = "Lấy thông tin 1 employer theo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResumeController.ResponseResume.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: id không tồn tại"
            ),

            }
    )
    @GetMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.resumeService.get(id);
        });
    }
    @Operation(
            summary = "Lấy danh sách employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ResumeController.ResponsePageResume.class
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
    public ResponseEntity<?> list( @RequestBody @Valid PageDtoIn pageDtoIn) {
        return responseEntity(() -> {
            return this.resumeService.list(pageDtoIn);
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
    @DeleteMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
        return responseEntity(() -> {
            this.resumeService.delete(id);
            return new HashMap<>();
        });
    }

    private static class ResponseResume extends com.myproject.employer.common.response.ApiResponse<ResumeDtoOut>{

    }
    private static class ResponsePageResume extends com.myproject.employer.common.response.ApiResponse<PageDtoOut<ResumeController>>{

    }
}
