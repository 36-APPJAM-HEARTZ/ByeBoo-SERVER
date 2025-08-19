package com.heartz.byeboo.security.usecase;

import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.security.command.OAuthCommand;
import com.heartz.byeboo.security.jwt.Token;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface OAuthUseCase {
    UserLoginResponse login(OAuthCommand command);
}
