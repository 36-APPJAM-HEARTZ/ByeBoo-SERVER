package com.heartz.byeboo.adapter.in.web.dto.request.gemini;

import java.util.List;

public record GeminiResponseDto(
        List<Candidate> candidates
) {
}

