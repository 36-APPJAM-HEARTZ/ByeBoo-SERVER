package com.heartz.byeboo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GcsConfig {

    @Value("${spring.cloud.gcp.credentials.location}")
    private String location;

    @Value("${spring.cloud.gcp.storage.project-id}")
    private String projectId;

    @Bean
    public Storage storage() throws IOException {
        ClassPathResource resource = new ClassPathResource(location);
        InputStream inputStream = resource.getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);

        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build()
                .getService();
    }
}
