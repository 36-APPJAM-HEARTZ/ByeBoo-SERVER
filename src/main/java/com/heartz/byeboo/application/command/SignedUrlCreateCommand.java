package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.request.SignedUrlRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import lombok.Builder;
import lombok.Getter;

import static com.heartz.byeboo.domain.exception.UserQuestErrorCode.INVALID_SIGNED_URL;

@Builder
@Getter
public class SignedUrlCreateCommand {
    private String imageKey;
    private String contentType;
    private Long userId;

    public static SignedUrlCreateCommand of(SignedUrlRequestDto signedUrlRequestDto, Long userId){
        try{
            return SignedUrlCreateCommand.builder()
                    .imageKey(signedUrlRequestDto.imageKey().toString())
                    .contentType(signedUrlRequestDto.contentType())
                    .userId(userId)
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(UserQuestErrorCode.INVALID_SIGNED_URL);
        }
    }
}
