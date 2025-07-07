package com.heartz.byeboo.application.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;
import com.heartz.byeboo.application.port.in.GcsUseCase;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GcsService implements GcsUseCase {

    private final Storage storage;
    private final RetrieveUserPort retrieveUserPort;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Override
    public SignedUrlResponseDto createSignedUrl(SignedUrlCreateCommand command) {
        retrieveUserPort.getUserById(command.getUserId());
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, command.getImageKey())).build();

        Map<String, String> extensionHeaders = new HashMap<>();

        extensionHeaders.put("Content-Type", command.getContentType());

        URL url = storage.signUrl(blobInfo,
                15,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withExtHeaders(extensionHeaders),
                Storage.SignUrlOption.withV4Signature());

        return SignedUrlResponseDto.of(url.toString());
    }
}
