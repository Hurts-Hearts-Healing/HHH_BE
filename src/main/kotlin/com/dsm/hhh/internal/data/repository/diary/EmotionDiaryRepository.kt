package com.dsm.hhh.internal.data.repository.diary

import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * EmotionDiaryRepository - 감정 일기 도메인 리포지토리 인터페이스
 * <p>
 * 도메인 계층에서 사용할 사용자 관련 데이터 접근 기능을 정의합니다.
 * 구현체에서 실제 데이터 저장소 연동을 처리합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
interface EmotionDiaryRepository {

    fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void>

    fun findByUserId(userId: UserId): Flux<EmotionDiaryInternalDTO>

    fun findByUserIdAndCreatedAt(userId: UserId, createdAt: CreatedAt): Mono<EmotionDiaryInternalDTO>

    fun findById(diaryId: DiaryId): Mono<EmotionDiaryInternalDTO>

}