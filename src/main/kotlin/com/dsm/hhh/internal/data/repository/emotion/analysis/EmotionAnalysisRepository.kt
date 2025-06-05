package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Mono

interface EmotionAnalysisRepository {
    fun save(emotionAnalysisInternalDTO: EmotionAnalysisInternalDTO): Mono<Void>

    fun findById(diaryId: String): Mono<EmotionAnalysisInternalDTO>
}