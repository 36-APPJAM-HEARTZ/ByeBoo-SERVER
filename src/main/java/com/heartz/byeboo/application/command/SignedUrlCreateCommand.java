package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.request.SignedUrlRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignedUrlCreateCommand {
    private String imageKey;
    private String contentType;
    private Long userId;

    public static SignedUrlCreateCommand of(SignedUrlRequestDto signedUrlRequestDto, Long userId){
        return SignedUrlCreateCommand.builder()
                .imageKey(signedUrlRequestDto.imageKey().toString())
                .contentType(signedUrlRequestDto.contentType())
                .userId(userId)
                .build();
    }
}
