package com.heartz.byeboo.adapter.in.web.dto;

public record SignedUrlResponseDto(
        String signedUrl
) {
    public static SignedUrlResponseDto of(String signedUrl){
        return new SignedUrlResponseDto(signedUrl);
    }
}
