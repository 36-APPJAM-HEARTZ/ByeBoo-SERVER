package com.heartz.byeboo.application.port.out;

public interface CreateObjectPort {
    String createSignedUrl(String imageKey, String contentType);
}
