package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserCommonQuest {
    private Long id;
    private String answer;
    private User user;
    private CommonQuest commonQuest;
    private LocalDateTime createdDate;

    public static UserCommonQuest of(
            Long id,
            String answer,
            User user,
            CommonQuest commonQuest,
            LocalDateTime createdDate
    ){
        return UserCommonQuest.builder()
                .id(id)
                .answer(answer)
                .user(user)
                .commonQuest(commonQuest)
                .createdDate(createdDate)
                .build();
    }


    public void updateAnswer(String answer){
        this.answer = answer;
    }
}
