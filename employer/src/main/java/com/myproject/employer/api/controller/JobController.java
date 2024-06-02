package com.myproject.employer.api.controller;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.job.JobDtoIn;
import com.myproject.employer.api.dto.in.job.UpdateJobDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.job.JobDtoOut;
import com.myproject.employer.api.service.job.JobService;
import com.myproject.employer.common.controller.AbstractResponseController;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.HashMap;

@RestController
@RequestMapping(value = "/job", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "job", description = "Quản lý Job")
public class JobController extends AbstractResponseController {


    private JobService jobService;

    @Autowired

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @Operation(
            summary = "Thêm mới job",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseJob.class)
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: employer_id đã tồn tại hoặc employer_id không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình thêm mới employer"
                    )
            }
    )
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid JobDtoIn jobDtoIn){
        return responseEntity(() -> {
            return this.jobService.create(jobDtoIn);
        }, HttpStatus.CREATED);

    }
    @Operation(
            summary = "Lấy thông tin theo id",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseJob.class)
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: employer_id đã tồn tại hoặc employer_id không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình thêm mới employer"
                    )
            }
    )
    @GetMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable (value = "id") Long id) {
        return responseEntity(() -> {
            return this.jobService.get(id);
                });

    }
    @Operation(
            summary = "Thêm mới job",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseJob.class)
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: employer_id đã tồn tại hoặc employer_id không tồn tại"
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Lỗi: có lỗi xảy ra trong quá trình thêm mới employer"
                    )
            }
    )
    @PutMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateJobDtoIn updateJobDtoIn){
        return responseEntity(() -> {
            return this.jobService.update(id, updateJobDtoIn);
                }
                );
    }
    @Operation(
            summary = "Lấy danh sách employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = JobController.ResponsePageJob.class
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
    public ResponseEntity<?> list(@RequestBody  @Valid PageDtoIn pageDtoIn){
        return responseEntity(() -> {
            return  this.jobService.list(pageDtoIn);
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
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            this.jobService.delete(id);
            return new HashMap();
        });
    }


    private static class ResponseJob extends com.myproject.employer.common.response.ApiResponse<JobDtoOut>{

    }
    private static class ResponsePageJob extends com.myproject.employer.common.response.ApiResponse<PageDtoOut<JobDtoOut>>{

    }
}
