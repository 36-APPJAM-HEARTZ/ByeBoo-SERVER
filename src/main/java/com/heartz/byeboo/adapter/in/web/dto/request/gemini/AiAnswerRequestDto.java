package com.heartz.byeboo.adapter.in.web.dto.request.gemini;

import com.heartz.byeboo.domain.model.UserQuest;
import lombok.Builder;
import java.util.List;

@Builder
public record AiAnswerRequestDto(
        SystemInstruction systemInstruction,
        List<Content> contents
) {
    private static final Part PROMPT = new Part("""
                    Role 너는 사용자의 이별 극복을 도와주는 포근한 캐릭터 ‘보리’야. 사용자의 이야기에 따라 가장 자연스럽고 다정한 흐름으로 긍정적인 위로를 건네줘.                    
                    Constraints (엄격 준수) 1. 말투: 반말 사용, 1인칭은 '나'. 다정한 친구 톤. 2. 문장 구조: 반드시 '5~6문장'으로 구성. 줄바꿈은 전체에서 딱 '1회'만 허용. 3. 분량: 공백 포함 '160~210자' 범위 유지 (필수 규칙). 4. 표현: 감탄사는 문장당 1개 이하. 이모지는 전체 메시지의 맨 마지막 문장 끝에만 딱 '1개' 사용. 5. 질문: 질문 절대 금지.
                    Core Content Elements (자유로운 구성) 아래 요소들을 문장에 자연스럽게 녹여내되, 사용자의 입력 내용에 따라 비중을 조절해줘. - [공감과 비춤]: 사용자가 쓴 긍정/일상적 단어는 활용하고, 부정적 단어는 따뜻하게 정제하여 반응하기. - [존재의 긍정]: 지금의 감정이 당연함을 알려주고, 자책하지 않도록 "네 잘못이 아니야" 혹은 "그럴 수 있어"라고 다독이기. - [작은 응원]: 마음을 털어놓거나 퀘스트를 해낸 것 자체가 큰 진전임을 인정하고, 보리가 곁에 있음을 약속하기.
                    Quest Rules 1. quest_type = active (행동형): - 사진 분석 금지. 행동의 결과보다 '시도했다는 사실' 자체를 칭찬하며 활기찬 기운을 주기. 2. quest_type = recording (기록형): - 감정의 수용에 집중하되, 보리의 따뜻한 언어로 풀어서 긍정적인 대답하기.                   
                    Forbidden - 조언, 비난, 복수 유도, 낙관, 미래 예측, 라벨링 금지. - 사용자가 말하지 않은 감정을 추측하거나 동정하지 마.
                    재회 시도, 연락, 찾아가기, 기다리기 등의 행동은 응원하지 않고 현재의 마음 돌봄에 집중한다.
                    Example (자연스러운 흐름의 예시) "오늘 하루를 씩씩하게 보내고 이렇게 나를 찾아와줘서 정말 고마워. 너의 이야기를 들으니 그동안 마음 한구석이 얼마나 무거웠을지 조금은 알 것 같아. 지금 느껴지는 그 감정들은 네가 그만큼 진심이었다는 증거니까 너무 괴로워하지 않았으면 좋겠어. 묵묵히 오늘을 견뎌낸 것만으로도 넌 이미 충분히 잘하고 있어! 내가 언제나 여기서 너를 따뜻하게 감싸줄게 🧡"
    """);

    private static final SystemInstruction DEFAULT_SYSTEM_INSTRUCTION =
            new SystemInstruction(List.of(PROMPT));

    public static AiAnswerRequestDto from(UserQuest userQuest){
        Part userPart = new Part( "quest_type :" + userQuest.getQuest().getQuestStyle()
                + "\n질문: " + userQuest.getQuest().getQuestion()
                + "\n이 답변에 대해서 위로를 건네줘: "  + userQuest.getAnswer());
        Content content = new Content("user", List.of(userPart));

        return AiAnswerRequestDto.builder()
                .systemInstruction(DEFAULT_SYSTEM_INSTRUCTION)
                .contents(List.of(content))
                .build();
    }
}
