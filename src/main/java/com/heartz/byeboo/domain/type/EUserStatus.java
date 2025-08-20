package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EUserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;
}
