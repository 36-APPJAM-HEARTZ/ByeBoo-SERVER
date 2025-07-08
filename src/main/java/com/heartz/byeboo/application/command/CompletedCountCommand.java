package com.heartz.byeboo.application.command;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompletedCountCommand {
    private Long id;

    public static CompletedCountCommand of(Long id){
        return CompletedCountCommand.builder()
                .id(id)
                .build();
    }
}
