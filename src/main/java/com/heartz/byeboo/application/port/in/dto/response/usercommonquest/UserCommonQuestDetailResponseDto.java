package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.type.EProfileIcon;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCommonQuestDetailResponseDto(
        Long answerId,
        String writer,
        String profileIcon,
        LocalDate writtenAt,
        String content
) {
    public static UserCommonQuestDetailResponseDto from(UserCommonQuest userCommonQuest, User user){
        return UserCommonQuestDetailResponseDto.builder()
                .answerId(userCommonQuest.getId())
                .writer(user.getName())
                .profileIcon(user.getProfileIcon().name())
                .writtenAt(userCommonQuest.getCreatedDate().toLocalDate())
                .content(userCommonQuest.getAnswer())
                .build();
    }
}
