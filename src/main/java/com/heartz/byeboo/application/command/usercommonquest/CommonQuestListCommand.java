package com.heartz.byeboo.application.command.usercommonquest;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonQuestListCommand {
    Long userId;
    LocalDate targetDate;
    Long cursor;
    int limit;

    public static CommonQuestListCommand from(Long userId, LocalDate targetDate, Long cursor, int limit){
        return CommonQuestListCommand.builder()
                .userId(userId)
                .targetDate(targetDate)
                .cursor(cursor)
                .limit(limit)
                .build();
    }
}
