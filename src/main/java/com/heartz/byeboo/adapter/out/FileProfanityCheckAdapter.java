package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.core.common.out.port.ProfanityCheckPort;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class FileProfanityCheckAdapter implements ProfanityCheckPort {
    private Set<String> badWords = new HashSet<>();
    private static final String FILE_PATH = "badwords.txt";

    @PostConstruct
    public void init() {
        loadWordsFromFile();
    }

    public void loadWordsFromFile() {
        Set<String> tempSet = new HashSet<>();
        try {
            ClassPathResource resource = new ClassPathResource(FILE_PATH);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            );

            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();

                // 빈 줄이나 주석(#) 처리된 줄은 무시
                if (!word.isEmpty() && !word.startsWith("#")) {
                    tempSet.add(word);
                }
            }

            // 읽어온 데이터를 실제 서비스 셋에 교체
            this.badWords = tempSet;
            log.info("비속어 파일 로드 완료: 총 {}개 단어", badWords.size());

        } catch (Exception e) {
            log.error("비속어 파일을 읽어오는 데 실패했습니다.", e);
        }
    }

    // 퀘스트 답변 저장 전 호출할 검증 메서드
    @Override
    public boolean containsBadWord(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        // 띄어쓰기를 악용한 우회 방지를 위해 공백 제거 후 검사
        String noSpaceText = text.replaceAll("\\s+", "");

        for (String badWord : badWords) {
            if (noSpaceText.contains(badWord)) {
                return true;
            }
        }
        return false;
    }
}
