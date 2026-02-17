package com.heartz.byeboo.application.command.quest;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.type.EJourney;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllQuestCompletedCommand {
    private Long userId;
    private EJourney journey;

    public static AllQuestCompletedCommand of(Long userId, String journey) {
        try {
            return AllQuestCompletedCommand.builder()
                    .userId(userId)
                    .journey(EJourney.valueOf(journey))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(UserJourneyErrorCode.INVALID_JOURNEY_TYPE);
        }
    }
}