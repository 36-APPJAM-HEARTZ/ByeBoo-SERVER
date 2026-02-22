package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.ReportStatusUpdateRequestDto;
import com.heartz.byeboo.application.command.report.ReportUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.ReportUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin API", description = "관리자용 API입니다.(프론트 연동X)")
public class AdminController {
    private final ReportUseCase reportUseCase;

    @Operation(
            summary = "관리자용 신고 상태 업데이트",
            description = "관리자용 신고 상태 업데이트를 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "관리자용 신고 상태 업데이트 성공"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PatchMapping("/reports/common-quests/{reportId}")
    public BaseResponse<Void> updateReportStatus(
            @PathVariable("reportId") final Long reportId,
            @RequestBody final ReportStatusUpdateRequestDto reportStatusUpdateRequestDto
            ){
        ReportUpdateCommand reportUpdateCommand = ReportUpdateCommand.from(reportId, reportStatusUpdateRequestDto);
        return BaseResponse.success(reportUseCase.updateReportStatus(reportUpdateCommand));
    }
}
