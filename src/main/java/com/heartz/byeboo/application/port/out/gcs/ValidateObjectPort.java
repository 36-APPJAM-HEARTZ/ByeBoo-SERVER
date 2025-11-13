package com.heartz.byeboo.application.port.out.gcs;

public interface ValidateObjectPort {
    boolean isObjectExists(String imageKey);
}
