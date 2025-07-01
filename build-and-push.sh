#!/bin/bash

# === 설정 ===
DOCKER_USERNAME="heartzbyeboo"        # ← 본인의 DockerHub ID로 변경
IMAGE_NAME="byeboo"
TAG="latest"

# === 1. jar 빌드 ===
echo "[+] Gradle 빌드 시작..."
./gradlew clean bootJar || { echo "[!] 빌드 실패"; exit 1; }

# === 2. Docker 이미지 빌드 ===
echo "[+] Docker 이미지 빌드 시작..."
docker build --build-arg JAR_FILE=build/libs/byeboo.jar -t $DOCKER_USERNAME/$IMAGE_NAME:$TAG .

# === 3. DockerHub 로그인 ===
echo "[+] DockerHub 로그인..."
docker login || { echo "[!] 로그인 실패"; exit 1; }

# === 4. 이미지 푸시 ===
echo "[+] DockerHub에 푸시 중..."
docker push $DOCKER_USERNAME/$IMAGE_NAME:$TAG || { echo "[!] 푸시 실패"; exit 1; }

echo "[✅] 완료! -> docker.io/$DOCKER_USERNAME/$IMAGE_NAME:$TAG"