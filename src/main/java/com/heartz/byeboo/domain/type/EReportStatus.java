package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EReportStatus {

    PENDING("검토중"), BLOCKED("차단됨"), REVIEWED_SAFE("검토완료_이상없음");

    private final String label;
}
