package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EJourney {
    FACE_EMOTION(
            "이별 극복",
            """
                       너무 힘든 시간을 보내고 있는 당신에게,
                       ‘이별 극복 여정’을 추천해요.
                       
                       이 여정은 감정을 직면하고,
                       상황을 정리하며,
                       점차 앞으로 나아 가는
                       5단계로 구성되어 있어요.
                       
                       하루에 하나씩 기록해 나가다 보면,
                       감정이 조금씩 정돈되고
                       마음이 가벼워질 거예요."""
    ),

    @Deprecated
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
                       마음도 조금씩 정리될 거예요."""
            ),
    PREPARE_REUNION(
            "재회 준비",
            """
                       X와 다시 시작하고 싶지만,
                       같은 아픔이 반복될까 두려운
                       당신에게 '재회 준비 여정'을 추천해요.
                       
                       이 여정은 우리 관계의 문제를 돌아보고,
                       개선점을 찾으며, 진심을 전할 준비를 갖춰가는
                       5단계로 구성되어 있어요.
                       
                       하루에 하나씩 써 내려가다 보면,
                       끝나가던 관계에 변화를 가져올
                       실마리가 보일 거예요."""
    );


    private final String label;
    private final String description;
}
