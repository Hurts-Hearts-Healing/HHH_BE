package com.dsm.hhh.internal.core.usecase.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Mono

interface EmotionAnalysisUseCase {
    fun save(diaryId: String): Mono<Void>

    fun findFirstByUserIdAndDiaryId(userId: String, diaryId: String): Mono<EmotionAnalysisInternalDTO>
}