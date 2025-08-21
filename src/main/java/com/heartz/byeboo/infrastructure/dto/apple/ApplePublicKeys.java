package com.heartz.byeboo.infrastructure.dto.apple;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;

import java.util.List;

public record ApplePublicKeys(
        List<ApplePublicKey> keys
) {
    public ApplePublicKey getMatchedPublicKey(String kid, String alg) {
        return keys.stream()
                .filter(applePublicKey -> applePublicKey.kid().equals(kid) && applePublicKey.alg().equals(alg))
                .findAny()
                .orElseThrow(() -> new CustomException(AuthErrorCode.INVALID_APPLE_IDENTITY_TOKEN));
    }
}

