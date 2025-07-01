#!/bin/bash

# === 설정 ===
DOCKER_USERNAME="heartzbyeboo"
IMAGE_NAME="byeboo"
TAG="latest"

# === 1. jar 빌드 ===
echo "[+] Gradle 빌드 시작..."
./gradlew clean bootJar || { echo "[!] 빌드 실패"; exit 1; }

# === 2. Docker Buildx 빌더 설정 ===
echo "[+] Docker Buildx 빌더 확인 중..."
docker buildx ls | grep byeboo-builder >/dev/null 2>&1
if [ $? -ne 0 ]; then
  docker buildx create --name byeboo-builder --use
else
  docker buildx use byeboo-builder
fi

# === 3. DockerHub 로그인 ===
echo "[+] DockerHub 로그인..."
docker login || { echo "[!] 로그인 실패"; exit 1; }

# === 4. 이미지 빌드 및 푸시 (멀티 플랫폼) ===
echo "[+] 멀티 아키텍처 이미지 빌드 및 푸시 시작..."
docker buildx build \
  --platform linux/amd64,linux/arm64 \
  --build-arg JAR_FILE=build/libs/byeboo.jar \
  -t $DOCKER_USERNAME/$IMAGE_NAME:$TAG \
  --push .

echo "빌드 완료! -> docker.io/$DOCKER_USERNAME/$IMAGE_NAME:$TAG"