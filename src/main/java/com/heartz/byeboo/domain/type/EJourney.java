package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EJourney {
    FACE_EMOTION("감정 직면"),
    PROCESS_EMOTION("감정 정리");

    private final String label;
}
