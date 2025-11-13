package com.heartz.byeboo.application.port.out.gcs;

public interface RetrieveObjectPort {
    String getSignedUrl(String imageKey);
}
