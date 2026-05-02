package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.CommentCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.CommentUpdateRequestDto;
import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.application.command.comment.CommentUpdateCommand;
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
            final CommentCreateRequestDto commentCreateRequestDto,
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
            final CommentUpdateRequestDto commentUpdateRequestDto,
            @UserId Long userId,
            @PathVariable Long commentId
    ){
        CommentUpdateCommand command = CommentUpdateCommand.of(userId, commentUpdateRequestDto,commentId);
        return BaseResponse.success(commentUseCase.updateComment(command));
    }

}
