package com.heartz.byeboo.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartz.byeboo.core.common.BaseResponse;
import com.heartz.byeboo.core.exception.ErrorCode;
import com.heartz.byeboo.core.exception.GlobalErrorCode;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        handleException(response);
    }

    private void handleException(HttpServletResponse response) throws IOException {
        setResponse(response, HttpStatus.UNAUTHORIZED, AuthErrorCode.UNAUTHORIZED);
    }

    private void setResponse(HttpServletResponse response, HttpStatus httpStatus, AuthErrorCode authErrorCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(httpStatus.value());
        response.getWriter().write(objectMapper.writeValueAsString(BaseResponse.fail(authErrorCode)));
    }
}
