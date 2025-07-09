package com.heartz.byeboo.application.command;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.type.EJourney;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JourneyUpdateCommand {

    private Long userId;
    private EJourney journey;

    public static JourneyUpdateCommand of(Long userId, String journey){
        try {
            return JourneyUpdateCommand.builder()
                    .userId(userId)
                    .journey(EJourney.valueOf(journey))
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(UserJourneyErrorCode.INVALID_USER_JOURNEY);
        }
    }
}
