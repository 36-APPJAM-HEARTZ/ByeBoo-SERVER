package com.heartz.byeboo.infrastructure.api.apple;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;

@Component
public class ApplePrivateKeyGenerator {

    @Value("${oauth.apple.private-key}")
    private String privateKey;

    protected PrivateKey getPrivateKey() {
        if (privateKey == null || privateKey.isBlank()) {
            throw new CustomException(AuthErrorCode.APPLE_INVALID_PRIVATE_KEY);
        }
        try (InputStream in = new ClassPathResource(privateKey).getInputStream();
             PEMParser pem = new PEMParser(new InputStreamReader(in))) {

            Object obj = pem.readObject();
            if (obj instanceof PrivateKeyInfo pki) {
                return new JcaPEMKeyConverter().getPrivateKey(pki);
            }
            throw new CustomException(AuthErrorCode.APPLE_INVALID_PRIVATE_KEY);
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.FAILED_TO_LOAD_PRIVATE_KEY);
        }
    }
}
