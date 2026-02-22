package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCommonQuestResponseDto(
        String question,
        String writer,
        String profileIcon,
        LocalDate writtenAt,
        String content,
        Long writerId
) {
    public static UserCommonQuestResponseDto from(UserCommonQuest userCommonQuest, CommonQuest commonQuest, User user){
        return UserCommonQuestResponseDto.builder()
                .content(userCommonQuest.getAnswer())
                .writtenAt(userCommonQuest.getCreatedDate().toLocalDate())
                .question(commonQuest.getQuestion())
                .profileIcon(user.getProfileIcon().name())
                .writer(user.getName())
                .writerId(user.getId())
                .build();
    }
}