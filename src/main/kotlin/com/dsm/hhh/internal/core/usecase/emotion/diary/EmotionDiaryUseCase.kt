package com.dsm.hhh.internal.core.usecase.emotion.diary

import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EmotionDiaryUseCase {

    fun write(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void>

    fun getMyDiaries(): Flux<EmotionDiaryInternalDTO>

}