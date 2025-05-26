package com.dsm.hhh.internal.data.repository.emotion.emoji

import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EmotionEmojiRepository {

    fun save(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): Mono<Void>

    fun findByUserId(userId: UserId): Flux<EmotionEmojiInternalDTO>

    fun findByUserIdAndCreatedAt(userId: UserId, createdAt: CreatedAt): Mono<EmotionEmojiInternalDTO>

}