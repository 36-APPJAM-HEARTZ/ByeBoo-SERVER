package com.heartz.byeboo.adapter.in.web.dto.request;

public record ReportCreateRequestDto(
        String targetType,
        Long targetId
) {
}
