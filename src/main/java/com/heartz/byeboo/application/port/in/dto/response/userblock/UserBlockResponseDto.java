package com.heartz.byeboo.application.port.in.dto.response.userblock;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserBlockInfoProjection;
import lombok.Builder;

@Builder
public record UserBlockResponseDto(
        String name,
        Long blockId
) {
    public static UserBlockResponseDto of(UserBlockInfoProjection userBlockInfoProjection){
        return UserBlockResponseDto.builder()
                .name(userBlockInfoProjection.getUserName())
                .blockId(userBlockInfoProjection.getUserBlockId())
                .build();
    }
}
