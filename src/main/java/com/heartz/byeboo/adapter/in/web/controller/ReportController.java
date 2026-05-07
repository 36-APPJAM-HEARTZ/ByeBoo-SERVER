package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.ReportCreateRequestDto;
import com.heartz.byeboo.application.command.report.CommonQuestReportCreateCommand;
import com.heartz.byeboo.application.command.report.ReportCreateCommand;
import com.heartz.byeboo.application.port.in.usecase.ReportUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
@Tag(name = "Report API", description = "Report 대한 API입니다.")
public class ReportController {

    private final ReportUseCase reportUseCase;

    @Operation(
            summary = "공통 퀘스트 게시글 신고",
            description = "공통 퀘스트 게시글 신고를 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 게시글 신고 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/v1/reports/common-quests/{answerId}")
    public BaseResponse<Void> reportCommonQuest(
            @UserId final Long userId,
            @PathVariable("answerId") Long answerId
            ){
        CommonQuestReportCreateCommand commonQuestReportCreateCommand = CommonQuestReportCreateCommand.from(userId, answerId);
        return BaseResponse.success(reportUseCase.reportCommonQuest(commonQuestReportCreateCommand));
    }

    @Operation(
            summary = "공통 퀘스트 게시글 신고 V2",
            description = "공통 퀘스트 게시글 신고를 위한 API입니다.(V2)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 게시글 신고 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/v2/reports")
    public BaseResponse<Void> reportCommonQuestV2(
            @UserId final Long userId,
            @RequestBody final ReportCreateRequestDto reportCreateRequestDto
            ){
        ReportCreateCommand reportCreateCommand = ReportCreateCommand.of(userId, reportCreateRequestDto);
        return BaseResponse.success(reportUseCase.report(reportCreateCommand));
    }

}
