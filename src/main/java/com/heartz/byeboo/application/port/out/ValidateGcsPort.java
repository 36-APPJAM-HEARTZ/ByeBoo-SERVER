package com.heartz.byeboo.application.port.out;

public interface ValidateGcsPort {
    boolean isObjectExists(String imageKey);
}
