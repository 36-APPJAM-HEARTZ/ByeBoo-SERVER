package com.heartz.byeboo.application.command.userquest;

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
