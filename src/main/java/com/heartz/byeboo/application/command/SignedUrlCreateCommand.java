package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.SignedUrlRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignedUrlCreateCommand {
    String imageKey;
    String contentType;
    Long userId;
    Long questId;

    public static SignedUrlCreateCommand from(SignedUrlRequestDto signedUrlRequestDto, Long questId, Long userId){
        return SignedUrlCreateCommand.builder()
                .imageKey(signedUrlRequestDto.imageKey())
                .contentType(signedUrlRequestDto.contentType())
    }
}
