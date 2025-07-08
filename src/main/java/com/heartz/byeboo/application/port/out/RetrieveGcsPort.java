package com.heartz.byeboo.application.port.out;

public interface RetrieveGcsPort {
    String getSignedUrl(String imageKey);
}
