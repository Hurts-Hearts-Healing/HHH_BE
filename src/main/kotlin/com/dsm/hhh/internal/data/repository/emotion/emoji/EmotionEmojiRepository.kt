package com.dsm.hhh.internal.data.repository.emotion.emoji

import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.EmotionAggregationResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

interface EmotionEmojiRepository {

    fun save(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): Mono<Void>

    fun findByUserId(userId: UserId): Flux<EmotionEmojiInternalDTO>

    fun findByUserIdAndCreatedAt(userId: UserId, createdAt: CreatedAt): Mono<EmotionEmojiInternalDTO>

    fun getEmotionStatsByDayPeriod(startDate: LocalDate, endDate: LocalDate): Flux<EmotionAggregationResult>

    fun getEmotionStatsByWeekPeriod(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flux<EmotionAggregationResult>

    fun getEmotionStatsByMonthPeriod(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flux<EmotionAggregationResult>
}