package com.dsm.hhh.external.web.rest.emotion.analysis.response

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import java.time.LocalDate

data class EmotionAnalysisResponse(
    val userId: String,
    val diaryId: String,
    val emotion: String,
    val createdAt: LocalDate
)

data class EmotionAnalysisListResponse(
    val analysis : List<EmotionAnalysisResponse>
)