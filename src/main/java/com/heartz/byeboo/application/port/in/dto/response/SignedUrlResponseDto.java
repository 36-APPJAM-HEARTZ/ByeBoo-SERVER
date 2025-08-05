package com.heartz.byeboo.application.port.in.dto.response;

public record SignedUrlResponseDto(
        String signedUrl
) {
    public static SignedUrlResponseDto of(String signedUrl){
        return new SignedUrlResponseDto(signedUrl);
    }
}
