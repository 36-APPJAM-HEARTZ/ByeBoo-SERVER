package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.CommonQuestEntity;
import com.heartz.byeboo.domain.model.CommonQuest;

public class CommonQuestMapper {
    public static CommonQuest toDomain(CommonQuestEntity commonQuestEntity){
        return CommonQuest.of(
                commonQuestEntity.getId(),
                commonQuestEntity.getQuestion(),
                commonQuestEntity.getTargetDate()
        );
    }

}
