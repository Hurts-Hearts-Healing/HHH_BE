# HHH

## Table of Contents
- [Introduce Project](#introduce-project)
- [Development Docs](#development-docs)
- [Architecture](#architecture)
    - [Architecture Structure](#architecture-structure)
    - [Layer Description](#layer-description)
    - [Bounded Context](#bounded-context)
    - [Tech Stack](#tech-stack)
- [Package Structure](#package-structure)
- [Unified Modeling Language](#unified-modeling-language)

---

## Introduce Project
AI기반 이별 후유증 극복 서비스 HHH입니다.

해당 레포지토리는 HHH 서비스의 백엔드 파트입니다.

## Development Docs
- [Domain Primitive가 팀 컨벤션까지 확장된 이유 및 근거 정리 - 김승원](https://orange-middle-cbd.notion.site/Domain-Primitive-1c069c2ee7df80eda993f09acad7738f?pvs=4)
- [Password Encoder를 Spring Bean으로 관리하지 않는 이유 및 근거 정리 - 김승원](https://orange-middle-cbd.notion.site/Password-Encoder-Spring-Bean-1c269c2ee7df800d95a0cbc6570cc869?pvs=4)
- [Utility Class에 Object 키워드를 사용하지 않는 이유 및 근거 정리 - 김승원](https://orange-middle-cbd.notion.site/Utility-Class-Object-1dc69c2ee7df8013b368c4bfdb3fddb8?pvs=4)

## Architecture

### Architecture Structure
``` 
[클라이언트] <-> [외부 계층(External Layer)] <-> [내부 코어(Internal Core)] <-> [데이터 계층(Data Layer)]
```

### Layer Description:
1. **External Layer**
    - 웹 컨트롤러, 보안 설정, 예외 처리
    - 외부 시스템과의 인터페이스 담당

2. **Internal Core**
    - 비즈니스 로직의 핵심
    - 도메인 모델, 유스케이스, 서비스 구현

3. **Data Layer**
    - 데이터 영속성 처리
    - MongoDB 리포지토리 구현

### Bounded Context
**User Management Context**

**Core:** 사용자 계정 관리, 이메일 인증, 로그인/로그아웃, 회원탈퇴
- **Core Subdomain**: 사용자 인증 및 보안
- **Generic Subdomain**: 이메일 인증 시스템 (재사용 가능한 모듈)

**Emotion Tracking Context**

**Core:** 감정 이모지 선택, 일기 작성/저장, 일기 목록 조회
- **Core Subdomain**: 감정 데이터 수집 및 관리

**Analytics & AI Context**

**Core:** 감정 변화 그래프 생성, AI 분석, 트렌드 리포트
- **Supporting Subdomain**: 데이터 시각화 및 분석 지원

### Tech Stack:
- **language**: Kotlin 2.1
- **framework**: Spring WebFlux
- **database**: MongoDB
- **build tool**: Gradle
- **etc.**: Docker

## Package Structure
``` 
com.dsm.hhh
├── Application.kt
├── external
│   ├── web (컨트롤러)
│   ├── security (보안 설정)
│   ├── error (에러 코드 정의)
│   └── exception (글로벌 예외 처리)
└── internal
    ├── core
    │   ├── domain
    │   │   ├── component (도메인 컴포넌트)
    │   │   ├── model (도메인 모델)
    │   │   └── service (서비스 구현체)
    │   └── usecase (유스케이스 인터페이스)
    ├── data
    │   └── repository (데이터 리포지토리)
    └── common (공통 유틸리티)
```

## Unified Modeling Language
- #### [UseCase Diagram](https://lucid.app/lucidchart/cac44dfd-cd7f-4f71-89dd-a0804d716efa/edit?viewport_loc=-5516%2C403%2C1583%2C1156%2C0_0&invitationId=inv_251ee9d1-3f79-420d-85c4-379aed4a681c)
- #### [Class Diagram](https://lucid.app/lucidchart/24615d80-820c-425f-91b6-c5286565d738/edit?viewport_loc=-63%2C-38%2C1902%2C1390%2C0_0&invitationId=inv_c6fb25e7-88d1-40a8-a4cd-039b5bd788cb)