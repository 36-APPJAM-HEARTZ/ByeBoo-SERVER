package com.heartz.byeboo.adapter.in.web.dto.response;

import com.heartz.byeboo.domain.model.Tip;

public record TipResponseDto(
        Integer tipStep,
        String tipQuestion,
        String tipAnswer
) {
    public static TipResponseDto of(Tip tip){
        return new TipResponseDto(tip.getTipStep(), tip.getTipQuestion(), tip.getTipAnswer());
    }
}

