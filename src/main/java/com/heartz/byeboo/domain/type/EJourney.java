package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EJourney {
    FACE_EMOTION(
            "감정 직면",
            """
                       너무 힘든 시간을 보내고 있는 당신에게,
                       ‘감정 직면 여정’을 추천해요.
                       
                       이 여정은 감정을 직면하고,
                       상황을 정리하며,
                       점차 앞으로 나아가는
                       5단계로 구성되어 있어요.
                       
                       하루에 하나씩 기록해 나가다 보면,
                       감정이 조금씩 정돈되고
                       마음이 가벼워질 거예요.
                       """
    ),
    PROCESS_EMOTION(
            "감정 정리",
            """
                       너무 힘든 시간을 보내고 있는 당신에게,
                       ‘감정 정리 여정’을 추천해요.
                       
                       이 여정은 몸을 움직이며
                       감정을 정리해 나가는
                       5단계로 구성되어 있어요.
                       
                       하루에 하나씩 작은 행동을
                       실천하다 보면,
                       머릿속이 맑아지고
                       마음도 조금씩 정리될 거예요.
                       """
            );

    private final String label;
    private final String description;
}
