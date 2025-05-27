package com.dsm.hhh.internal.data.repository.emotion.diary

import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * EmotionDiaryRepository - 감정 일기 도메인 리포지토리 인터페이스
 * <p>
 * 감정 일기 관련 데이터 접근 기능을 정의합니다.
 * 구현체에서 실제 데이터 저장소 연동을 처리합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
interface EmotionDiaryRepository {

    /**
     * 감정 일기를 저장합니다.
     * @param emotionDiaryInternalDTO 저장할 감정 일기 데이터
     * @return 완료 신호
     */
    fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void>
    
    /**
     * 사용자 ID로 감정 일기를 조회합니다.
     * @param userId 사용자 ID
     * @return 감정 일기 목록
     */
    fun findByUserId(userId: UserId): Flux<EmotionDiaryInternalDTO>

    /**
     * 사용자 ID와 생성일로 감정 일기를 조회합니다.
     * @param userId 사용자 ID
     * @param createdAt 생성일
     * @return 감정 일기
     */
    fun findByUserIdAndCreatedAt(userId: UserId, createdAt: CreatedAt): Mono<EmotionDiaryInternalDTO>

    /**
     * 일기 ID로 감정 일기를 조회합니다.
     * @param diaryId 일기 ID
     * @return 감정 일기
     */
    fun findById(diaryId: DiaryId): Mono<EmotionDiaryInternalDTO>

}