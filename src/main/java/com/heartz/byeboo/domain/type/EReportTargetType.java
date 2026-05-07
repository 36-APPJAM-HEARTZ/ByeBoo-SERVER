package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EReportTargetType {

    COMMENT ("댓글"),
    COMMON_QUEST("공통 퀘스트 게시글");

    private final String label;

}
