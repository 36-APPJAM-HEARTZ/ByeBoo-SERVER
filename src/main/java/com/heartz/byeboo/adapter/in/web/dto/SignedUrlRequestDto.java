package com.heartz.byeboo.adapter.in.web.dto;

import java.util.UUID;

public record SignedUrlRequestDto(
        String contentType,
        UUID imageKey
) {
}
