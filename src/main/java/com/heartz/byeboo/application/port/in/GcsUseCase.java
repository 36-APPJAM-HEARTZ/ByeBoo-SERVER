package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;

public interface GcsUseCase {
    SignedUrlResponseDto createSignedUrl(SignedUrlCreateCommand command);
}
