package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.application.command.SignedUrlCreateCommand;

public interface CreateGcsPort {
    String createSignedUrl(String imageKey, String contentType);
}
