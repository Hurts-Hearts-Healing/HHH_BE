package com.dsm.hhh.internal.core.usecase.diary

import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import reactor.core.publisher.Mono

interface EmotionDiaryUseCase {

    fun write(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void>

}