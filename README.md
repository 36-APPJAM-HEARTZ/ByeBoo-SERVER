<img width="1200" src="https://github.com/user-attachments/assets/20e8a39a-78a1-4c88-bec4-3c84b7d257a5" />

<br><br>
<div align="center">
  
**이별 극복을 위한 단 하나의 가이드, `Bye Boo` 입니다. 🪨🪨**

`ByeBoo`는 이별의 감정을 맞춤형 퀘스트를 통해 정리하고, 감정 회복의 여정을 함께하는 `감정 케어 앱`입니다. <br>
사용자는 `자기 성찰형/행동 실천형 퀘스트`를 선택하여 보리와 함께 감정을 직면하고 일상을 회복해나갑니다.

</div>
<br><br>

# 👻 Server Developer
|👑 박상범 </br> [@ssangbaam](https://github.com/ssangbaam) |차현정 </br> [@jeong724](https://github.com/jeong724)|
|:---:|:---:|
| <img width="250" src="https://github.com/user-attachments/assets/70f3b43a-1205-4645-9990-9b9b66192372"/> | <img width="250" src="https://github.com/user-attachments/assets/915817de-9953-45b2-8cb0-7d322aa147c9"/> | 

<br>

# 🛠️ TECH STACK
- Hexagonal Architecture
- Blue-Green Deployment
- Java 17 (LTS)
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL (with Google Cloud SQL)
- Lombok
- Spring Cloud OpenFeign
- Google Cloud Storage SDK
- Springdoc OpenAPI (Swagger UI)
- Spring Boot Actuator + Micrometer + Prometheus
<br>

| 기술 스택 | 도입 이유 |
| --- | --- |
| **Hexagonal Architecture** | 기술적인 관심사로부터 비즈니스 로직을 분리하고, 테스트 용이성과 유지보수성을 확보하기 위해 도입했습니다. 도메인 중심 설계를 위한 기반 구조로 사용됩니다. |
| **도메인 중심 설계(DDD 적용 기반)** | 도메인과 엔티티를 분리하고, 도메인 계층에 핵심 로직을 집중시켜 유스케이스 중심의 흐름을 구성했습니다. 완전한 DDD 구현은 아니지만, 이를 지향하는 설계 구조를 따르고 있습니다. |
| **Blue-Green Deployment** | 서비스 무중단 배포를 위해 두 개의 독립적인 배포 환경을 구성하여, 새로운 버전의 안정성을 검증한 뒤 트래픽을 전환함으로써 다운타임 없이 안정적으로 배포할 수 있도록 구현했습니다. |

# 🧰 ARCHITECTURE
<img width="1748" height="1272" alt="image" src="https://github.com/user-attachments/assets/ebccfefa-7f7c-407b-b297-7a99708a30cd" />


# 📦 FOLDERING
```
📦 com.heartz.byeboo
┣ 📂 adapter
┃ ┣ 📂 in
┃ ┃ ┗ 📂 web
┃ ┃   ┣ 📂 controller
┃ ┃   ┗ 📂 dto
┃ ┃     ┗ 📂 request
┃ ┗ 📂 out
┃   ┗ 📂 persistence
┃     ┣ 📂 entity
┃     ┗ 📂 repository
┣ 📂 application
┃ ┣ 📂 command
┃ ┣ 📂 port
┃ ┃ ┣ 📂 in
┃ ┃ ┃ ┣ 📂 usecase
┃ ┃ ┃ ┗ 📂 dto
┃ ┃ ┃   ┗ 📂 response
┃ ┃ ┗ 📂 out
┃ ┗ 📂 service
┣ 📂 config
┣ 📂 constants
┣ 📂 core
┃ ┣ 📂 common
┃ ┣ 📂 exception
┃ ┗ 📂 interceptor
┣ 📂 domain
┃ ┣ 📂 exception
┃ ┣ 📂 model
┃ ┗ 📂 type
┣ 📂 infrastructure
┃ ┣ 📂 api
┃ ┗ 📂 dto
┣ 📂 mapper
┗ 📂 utils

```
# 📌 Convention
## 🧩 Commit
| 태그       | 설명                                                                 |
|------------|----------------------------------------------------------------------|
| `feat`     | 새로운 기능 구현 시 사용                                              |
| `style`    | 스타일 및 UI 기능 구현 시 사용                                        |
| `fix`      | 버그나 오류 해결 시 사용                                              |
| `docs`     | README, 템플릿 등 프로젝트 내 문서 수정 시 사용                        |
| `setting`  | 프로젝트 관련 설정 변경 시 사용                                       |
| `add`      | 사진 등 에셋이나 라이브러리 추가 시 사용                              |
| `refactor` | 기존 코드를 리팩토링하거나 수정할 때 사용                             |
| `chore`    | 별로 중요한 수정이 아닐 때 사용                             |
| `hotfix`   | 급하게 develop에 바로 반영해야 하는 경우 사용 |
## 🧩 Code
| 항목                 | 명명 규칙                     |
| ------------------ | ------------------------- |
| **Class**          | `PascalCase`              |
| **Method**         | `camelCase`               |
| **Variable**       | `camelCase`               |
| **DB Table**       | `snake_case` (소문자)        |
| **Enum**           | `UPPER_CASE` (대문자, 언더스코어) |
| **Collection 변수명** | 복수형 사용                    |
