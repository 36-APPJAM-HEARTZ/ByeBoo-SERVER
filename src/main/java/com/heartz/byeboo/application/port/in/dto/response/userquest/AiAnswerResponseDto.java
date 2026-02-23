package com.heartz.byeboo.application.port.in.dto.response.userquest;

public record AiAnswerResponseDto(
        String aiAnswer
) {
    public static AiAnswerResponseDto of(String aiAnswer){
        return new AiAnswerResponseDto(aiAnswer);
    }
}
