package com.heartz.byeboo.application.command.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminLoginCommand{
    private String id;
    private String password;

    public static AdminLoginCommand of(String id, String password){
        return AdminLoginCommand.builder()
                .id(id)
                .password(password)
                .build();
    }
}
