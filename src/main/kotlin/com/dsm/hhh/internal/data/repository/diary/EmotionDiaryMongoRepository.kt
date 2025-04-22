package com.dsm.hhh.internal.data.repository.diary

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * EmotionDiaryMongoRepository - Spring Data MongoDB Reactive Repository
 * <p>
 * MongoDB와의 반응형(Reactive) 연동을 제공하는 Spring Data 인터페이스
 * 기본 CRUD 연산을 지원하며 EmotionDiaryEntity를 관리합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
interface EmotionDiaryMongoRepository: ReactiveMongoRepository<EmotionDiaryEntity, String>