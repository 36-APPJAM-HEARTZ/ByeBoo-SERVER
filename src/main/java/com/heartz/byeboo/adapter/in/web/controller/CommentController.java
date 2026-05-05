package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.ReplyCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.CommentCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.CommentUpdateRequestDto;
import com.heartz.byeboo.application.command.comment.*;
import com.heartz.byeboo.application.port.in.dto.response.comment.ReplyListResponseDto;
import com.heartz.byeboo.application.port.in.usecase.CommentUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Tag(name = "COMMENT API", description = "COMMENT 대한 API입니다.")
public class CommentController {

    private final CommentUseCase commentUseCase;

    @Operation(
            summary = "댓글 작성",
            description = "댓글 작성하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "댓글 저장 성공"
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

    @PostMapping
    public BaseResponse<Void> createComment(
            @RequestBody final CommentCreateRequestDto commentCreateRequestDto,
            @UserId Long userId
            ){
        CommentCreateCommand command = CommentCreateCommand.of(userId, commentCreateRequestDto);
        return BaseResponse.success(commentUseCase.createComment(command));
    }

    @Operation(
            summary = "댓글 수정",
            description = "댓글 수정하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "댓글 수정 성공"
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

    @PatchMapping ("/{commentId}")
    public BaseResponse<Void> updateComment(
            @RequestBody final CommentUpdateRequestDto commentUpdateRequestDto,
            @UserId Long userId,
            @PathVariable Long commentId
    ){
        CommentUpdateCommand command = CommentUpdateCommand.of(userId, commentUpdateRequestDto,commentId);
        return BaseResponse.success(commentUseCase.updateComment(command));
    }

    @Operation(
            summary = "댓글 삭제",
            description = "댓글 삭제하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "댓글 삭제 성공"
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

    @DeleteMapping ("/{commentId}")
    public BaseResponse<Void> deleteComment(
            @UserId Long userId,
            @PathVariable Long commentId
    ){
        CommentDeleteCommand command = CommentDeleteCommand.of(userId, commentId);
        return BaseResponse.success(commentUseCase.deleteComment(command));
    }

    @Operation(
            summary = "답글 작성",
            description = "답글 작하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "답글 작성 성공"
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

    @PostMapping ("/{commentId}/replies")
    public BaseResponse<Void> createReply(
            @UserId Long userId,
            @PathVariable Long commentId,
            @RequestBody final ReplyCreateRequestDto replyCreateRequestDto
    ){
        ReplyCreateCommand command = ReplyCreateCommand.of(userId, commentId, replyCreateRequestDto);
        return BaseResponse.success(commentUseCase.createReply(command));
    }

    @Operation(
            summary = "답글 목록 조회",
            description = "답글 작하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "답글 목록 조회 성공"
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

    @GetMapping ("/{commentId}/replies")
    public BaseResponse<ReplyListResponseDto> getReply(
            @UserId Long userId,
            @PathVariable Long commentId
    ){
        ReplyListCommand command = ReplyListCommand.of(userId, commentId);
        return BaseResponse.success(commentUseCase.getReply(command));
    }

}
