package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestInfoProjection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserCommonQuestAnswerResponseDto(
        String writer,
        String profileIcon,
        LocalDateTime writtenAt,
        String content,
        Long writerId,
        int likeCount,
        boolean isLiked,
        int commentCount

) {
    public static UserCommonQuestAnswerResponseDto of( String writer, String profileIcon, LocalDateTime writtenAt, String content, Long writerId, int likeCount, boolean isLiked, int commentCount){
        return UserCommonQuestAnswerResponseDto.builder()
                .writer(writer)
                .profileIcon(profileIcon)
                .writtenAt(writtenAt)
                .content(content)
                .writerId(writerId)
                .likeCount(likeCount)
                .isLiked(isLiked)
                .commentCount(commentCount)
                .build();
    }
}
