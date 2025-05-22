package com.dsm.hhh.internal.core.usecase.emotion

import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EmotionUseCase {
    fun register(emotionInternalDTO: EmotionInternalDTO): Mono<Void>

    fun findByUserId(): Flux<EmotionInternalDTO>
}