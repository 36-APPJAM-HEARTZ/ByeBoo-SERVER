package com.heartz.byeboo.adapter.out;

import com.google.cloud.storage.*;
import com.heartz.byeboo.application.port.out.CreateGcsPort;
import com.heartz.byeboo.application.port.out.RetrieveGcsPort;
import com.heartz.byeboo.application.port.out.ValidateGcsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class GcsAdapter implements ValidateGcsPort, CreateGcsPort, RetrieveGcsPort{

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Override
    public boolean isObjectExists(String imageKey) {
        Blob blob = storage.get(BlobId.of(bucketName, imageKey));
        return blob != null && blob.exists();
    }

    @Override
    public String createSignedUrl(String imageKey, String contentType) {
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, imageKey)).build();

        Map<String, String> extensionHeaders = new HashMap<>();

        extensionHeaders.put("Content-Type", contentType);

        URL url = storage.signUrl(blobInfo,
                15,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withExtHeaders(extensionHeaders),
                Storage.SignUrlOption.withV4Signature());

        return url.toString();
    }

    @Override
    public String getSignedUrl(String imageKey) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, imageKey).build();

        URL signedUrl = storage.signUrl(
                blobInfo,
                10,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.GET)
        );

        return signedUrl.toString();
    }

}
