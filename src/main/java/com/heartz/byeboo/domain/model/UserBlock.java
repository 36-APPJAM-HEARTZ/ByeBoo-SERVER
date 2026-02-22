package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EReportStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBlock {

    private Long id;
    private User blockedUser;
    private User blockerUser;

    public static UserBlock of(Long id,User blockedUser, User blockerUser) {
        return UserBlock.builder()
                .id(id)
                .blockedUser(blockedUser)
                .blockerUser(blockerUser)
                .build();
    }
}
