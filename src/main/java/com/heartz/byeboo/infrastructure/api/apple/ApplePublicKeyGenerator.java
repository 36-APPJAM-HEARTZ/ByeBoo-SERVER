package com.heartz.byeboo.infrastructure.api.apple;

import com.heartz.byeboo.constants.AuthConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.core.exception.GlobalErrorCode;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import com.heartz.byeboo.infrastructure.dto.apple.ApplePublicKey;
import com.heartz.byeboo.infrastructure.dto.apple.ApplePublicKeys;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
public class ApplePublicKeyGenerator {


    protected PublicKey generatePublicKey(final Map<String, String> headers, final ApplePublicKeys applePublicKeys) {
        ApplePublicKey applePublicKey = applePublicKeys
                .getMatchedPublicKey(headers.get(AuthConstants.Apple.KEY_ID_HEADER_KEY), headers.get(AuthConstants.Apple.SIGN_ALGORITHM_HEADER_KEY));
        return getPublicKey(applePublicKey);
    }

    private PublicKey getPublicKey(final ApplePublicKey applePublicKey) {

        byte[] nBytes = Base64.getUrlDecoder().decode(applePublicKey.n());
        byte[] eBytes = Base64.getUrlDecoder().decode(applePublicKey.e());

        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(
                new BigInteger(1, nBytes), new BigInteger(1, eBytes));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(applePublicKey.kty());
            return keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(AuthErrorCode.UNSUPPORTED_ALGORITHM);
        } catch (InvalidKeySpecException e) {
            throw new CustomException(AuthErrorCode.INVALID_KEY_SPEC);
        } catch (Exception e) {
            throw new CustomException(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
