package com.heartz.byeboo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartz.byeboo.core.common.BaseResponse;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.core.exception.ErrorCode;
import com.heartz.byeboo.core.exception.GlobalErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            handleUnauthorizedException(response, e);
        } catch (Exception ee) {
            handleException(response, ee);
        }
    }

    private void handleUnauthorizedException(
            HttpServletResponse response,
            Exception e) throws IOException {
        CustomException ue = (CustomException) e;
        ErrorCode authErrorCode = ue.getErrorCode();
        HttpStatus httpStatus = authErrorCode.getStatus();
        setResponse(response, httpStatus, authErrorCode);
    }

    private void handleException(
            HttpServletResponse response,
            Exception e) throws IOException {
        setResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    private void setResponse(
            HttpServletResponse response,
            HttpStatus httpStatus,
            ErrorCode authErrorCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(httpStatus.value());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(BaseResponse.fail(authErrorCode)));
    }
}