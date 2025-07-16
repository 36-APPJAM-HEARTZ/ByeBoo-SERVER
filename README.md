<img width="681" height="190" alt="Frame 2087328739" src="https://github.com/user-attachments/assets/950f56f2-97ae-43b1-bb8f-abfe7b6a84b1" />

```
이별 극복을 위한 단 하나의 가이드, Bye Boo 입니다. 🪨🪨
```

# 👻 Server Developer
|👑 박상범 </br> [@ssangbaam](https://github.com/ssangbaam) |차현정 </br> [@jeong724](https://github.com/jeong724)|
|:---:|:---:|
| <img width="250" src="https://github.com/user-attachments/assets/70f3b43a-1205-4645-9990-9b9b66192372"/> | <img width="250" src="https://github.com/user-attachments/assets/915817de-9953-45b2-8cb0-7d322aa147c9"/> | 

<br>

# 🛠️ TECH STACK
- Hexagonal Architecture
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
┃ ┃     ┣ 📂 request
┃ ┃     ┗ 📂 response
┃ ┗ 📂 out
┃   ┗ 📂 persistence
┃     ┣ 📂 entity
┃     ┗ 📂 repository
┣ 📂 application
┃ ┣ 📂 command
┃ ┣ 📂 port
┃ ┃ ┣ 📂 in
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
