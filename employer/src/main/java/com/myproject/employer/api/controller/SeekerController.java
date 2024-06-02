package com.myproject.employer.api.controller;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.seeker.SeekerDtoIn;
import com.myproject.employer.api.dto.in.seeker.UpdateSeekerDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.seeker.SeekerDtoOut;
import com.myproject.employer.api.service.seeker.SeekerService;
import com.myproject.employer.api.service.seeker.SeekerServiceImpl;
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
@RequestMapping(value = "/seeker", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Seeker", description = "Quản lý Seeker")
public class SeekerController extends AbstractResponseController {
    private final SeekerService seekerService;
    @Autowired

    public SeekerController(SeekerService seekerService) {
        this.seekerService = seekerService;
    }
    @Operation(
            summary = "Thêm mới employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SeekerController.ResponseSeeker.class
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
    public ResponseEntity<?> create(@RequestBody @Valid SeekerDtoIn seekerDtoIn){
        return responseEntity(() -> {
            return this.seekerService.create(seekerDtoIn);
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
                                            implementation = SeekerController.ResponseSeeker.class
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
    public ResponseEntity<?> update(@PathVariable (value = "id") Long id, @RequestBody @Valid UpdateSeekerDtoIn updateSeekerDtoIn){
        return responseEntity(() -> {
            return this.seekerService.update(id, updateSeekerDtoIn);
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
                                            implementation = SeekerController.ResponseSeeker.class
                                    )
                            )}
                    ), @ApiResponse(
                    responseCode = "400",
                    description = "Lỗi: id không tồn tại"
            ),

            }
    )
    @GetMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable (value = "id") Long id) {
        return responseEntity(() -> {
            return this.seekerService.get(id);
        }) ;
    }
    @Operation(
            summary = "Lấy danh sách employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = SeekerController.ResponsePageSeeker.class
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
    @GetMapping(value = "{/id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list(@PathVariable (value = "id") Long id, @RequestBody @Valid PageDtoIn pageDtoIn) {
        return responseEntity(() -> {
            return this.seekerService.list(pageDtoIn);
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
    @DeleteMapping(value = "id", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
        return responseEntity(() -> {
            this.seekerService.delete(id);
            return new HashMap<>();
        });
    }

    private static class ResponseSeeker extends com.myproject.employer.common.response.ApiResponse<SeekerDtoOut>{

    }
    private static class ResponsePageSeeker extends com.myproject.employer.common.response.ApiResponse<PageDtoOut<SeekerDtoOut>>{

    }
}
