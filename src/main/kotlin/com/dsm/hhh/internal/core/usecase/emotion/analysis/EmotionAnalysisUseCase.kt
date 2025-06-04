package com.dsm.hhh.internal.core.usecase.emotion.analysis

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import reactor.core.publisher.Mono

interface EmotionAnalysisUseCase {
    fun save(diaryId: String): Mono<Void>

    fun findByUserIdAndDiaryId(diaryId: String): Mono<EmotionAnalysisInternalDTO>
}