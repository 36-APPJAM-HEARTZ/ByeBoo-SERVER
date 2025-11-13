package com.heartz.byeboo.application.port.out.gcs;

public interface CreateObjectPort {
    String createSignedUrl(String imageKey, String contentType);
}
