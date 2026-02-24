package com.heartz.byeboo.application.port.in.dto.response.userblock;

import lombok.Builder;

import java.util.List;

@Builder
public record UserBlockListResponseDto(
        List<UserBlockResponseDto> blockList
) {
    public static UserBlockListResponseDto of(List<UserBlockResponseDto> userBlockResponseDtoList){
        return UserBlockListResponseDto.builder()
                .blockList(userBlockResponseDtoList)
                .build();
    }
}
