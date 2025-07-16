package com.heartz.byeboo.application.port.in.dto.response.quest;

import com.heartz.byeboo.domain.model.Tip;

public record TipResponseDto(
        Integer tipStep,
        String tipAnswer
) {
    public static TipResponseDto from(Tip tip){
        return new TipResponseDto(tip.getTipStep(),tip.getTipAnswer());
    }
}

