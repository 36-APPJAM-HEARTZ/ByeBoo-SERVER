package com.heartz.byeboo.application.command;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HomeCountCommand {
    private Long id;

    public static HomeCountCommand of(Long id){
        return HomeCountCommand.builder()
                .id(id)
                .build();
    }
}
