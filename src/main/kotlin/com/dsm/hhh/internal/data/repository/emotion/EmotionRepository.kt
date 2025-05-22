package com.dsm.hhh.internal.data.repository.emotion

import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface EmotionRepository {
    fun save(emotionInternalDTO: EmotionInternalDTO): Mono<Void>

    fun findByUserId(userId: UserId): Flux<EmotionInternalDTO>
}