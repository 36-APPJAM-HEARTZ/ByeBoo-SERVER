package com.heartz.byeboo.application.port.out;

public interface RetrieveGcsPort {
    boolean isObjectExists(String imageKey);
}
