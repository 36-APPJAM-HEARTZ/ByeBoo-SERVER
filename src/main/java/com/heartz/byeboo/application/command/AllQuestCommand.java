package com.heartz.byeboo.application.command;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.type.EJourney;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllQuestCommand {
    private Long userId;
    private EJourney journey;

    public static AllQuestCommand of(Long userId, String journey) {
        try {
            return AllQuestCommand.builder()
                    .userId(userId)
                    .journey(EJourney.valueOf(journey))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(UserJourneyErrorCode.INVALID_JOURNEY_TYPE);
        }
    }
}
