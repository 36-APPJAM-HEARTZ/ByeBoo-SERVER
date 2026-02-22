package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestInfoProjection;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.type.EProfileIcon;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record UserCommonQuestDetailResponseDto(
        Long answerId,
        String writer,
        String profileIcon,
        LocalDateTime writtenAt,
        String content
) {
    public static UserCommonQuestDetailResponseDto of(UserCommonQuestInfoProjection userCommonQuestInfoProjection){
        return UserCommonQuestDetailResponseDto.builder()
                .answerId(userCommonQuestInfoProjection.getAnswerId())
                .writer(userCommonQuestInfoProjection.getWriter())
                .profileIcon(userCommonQuestInfoProjection.getProfileIcon().name())
                .writtenAt(userCommonQuestInfoProjection.getWrittenAt())
                .content(userCommonQuestInfoProjection.getContent())
                .build();
    }
}
