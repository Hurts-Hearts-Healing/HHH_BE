package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Mono

interface EmotionAnalysisRepository {
    fun save(emotionAnalysisInternalDTO: EmotionAnalysisInternalDTO): Mono<Void>

    fun findByUserIdAndDiaryId(userId: String, diaryId: String): Mono<EmotionAnalysisInternalDTO>
}