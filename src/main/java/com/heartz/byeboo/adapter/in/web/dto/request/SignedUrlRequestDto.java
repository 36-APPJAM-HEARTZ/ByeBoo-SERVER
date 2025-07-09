package com.heartz.byeboo.adapter.in.web.dto.request;

import java.util.UUID;

public record SignedUrlRequestDto(
        String contentType,
        UUID imageKey
) {
}
