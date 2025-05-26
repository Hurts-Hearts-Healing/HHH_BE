package com.dsm.hhh.internal.core.usecase.emotion.emoji

import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EmotionEmojiUseCase {

    fun register(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): Mono<Void>

    fun findByUserId(): Flux<EmotionEmojiInternalDTO>

}