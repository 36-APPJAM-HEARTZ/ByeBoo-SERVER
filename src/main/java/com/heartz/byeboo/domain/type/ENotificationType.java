package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ENotificationType {

    QUEST_OPEN(
            "오늘의 퀘스트 오픈 🌱",
            "%d번째 퀘스트가 오픈됐어요, 시작해볼까요?",
            "myapp://quest/%d"
    ),

    COMMENT(
            "공통여정에 답변이 달렸어요 💬",
            "내가 작성한 글에 %s님이 답변을 남겼어요",
            "myapp://common-quests/%d"
    ),

    LIKE(
            "공통여정 답변에 공감이 달렸어요 ❤️",
            "내가 작성한 글에 %s님이 공감을 남겼어요",
            "myapp://common-quests/%d"
    );

    private final String title;
    private final String contentTemplate;
    private final String landingUrlTemplate;

    public String createLandingUrl(Long targetId) {
        return String.format(landingUrlTemplate, targetId);
    }

    public String createContent(Long targetId, String actorNickname) {
        return switch (this) {
            case QUEST_OPEN -> String.format(contentTemplate, targetId);
            case COMMENT, LIKE -> String.format(contentTemplate, actorNickname);
        };
    }
}