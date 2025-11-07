# Java 17 기반 슬림 이미지 사용
FROM eclipse-temurin:17-jdk-jammy

# 작업 디렉토리 설정 (선택적)
WORKDIR /app

# jar 파일 복사
COPY build/libs/byeboo.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]