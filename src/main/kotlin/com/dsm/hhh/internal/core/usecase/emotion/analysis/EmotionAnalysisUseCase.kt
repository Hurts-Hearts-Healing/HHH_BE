package com.dsm.hhh.internal.core.usecase.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import reactor.core.publisher.Mono

interface EmotionAnalysisUseCase {
    fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void>
}