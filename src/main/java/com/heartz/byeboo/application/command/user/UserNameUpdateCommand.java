package com.heartz.byeboo.application.command.user;

import com.heartz.byeboo.adapter.in.web.dto.request.UserNameUpdateRequestDto;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserNameUpdateCommand {
    private Long id;
    private String name;

    public static UserNameUpdateCommand of(Long id, UserNameUpdateRequestDto userNameUpdateRequestDto){
        return UserNameUpdateCommand.builder()
                .id(id)
                .name(userNameUpdateRequestDto.name())
                .build();
    }
}
