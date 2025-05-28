package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import reactor.core.publisher.Mono

interface EmotionAnalysisRepository {
    fun save(emotionAnalysisInternalDTO: EmotionAnalysisInternalDTO): Mono<Void>
}