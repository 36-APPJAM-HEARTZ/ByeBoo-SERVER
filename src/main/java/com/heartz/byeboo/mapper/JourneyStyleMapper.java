package com.heartz.byeboo.mapper;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;

public class JourneyStyleMapper {

    public static EQuestStyle journeyToQuestStyle(EJourney journey){
        return switch (journey){
            case FACE_EMOTION -> EQuestStyle.RECORDING;
            case PROCESS_EMOTION -> EQuestStyle.ACTIVE;
            case PREPARE_REUNION -> EQuestStyle.REUNION;
        };
    }
}
