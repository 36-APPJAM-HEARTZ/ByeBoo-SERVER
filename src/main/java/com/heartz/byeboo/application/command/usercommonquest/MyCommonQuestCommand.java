package com.heartz.byeboo.application.command.usercommonquest;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MyCommonQuestCommand {
    Long userId;
    Long cursor;
    int limit;

    public static MyCommonQuestCommand from(Long userId, Long cursor, int limit){
        return MyCommonQuestCommand.builder()
                .userId(userId)
                .cursor(cursor)
                .limit(limit)
                .build();
    }
}
