package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.TipEntity;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.Tip;

public class TipMapper {
    public static Tip toDomain(TipEntity tipEntity, Quest quest){
        return Tip.of(
                tipEntity.getId(),
                tipEntity.getTipStep(),
                tipEntity.getTipQuestion(),
                tipEntity.getTipAnswer(),
                quest
        );
    }
}
