package com.dsm.hhh.external.web.rest.emotion.analysis.response

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt

data class EmotionAnalysisResponse(
    val userId: String,
    val diaryId: String,
    val emotion: String,
    val createdAt: CreatedAt?
)

class EmotionAnalysisGroupResponse(
    val analysis : EmotionAnalysisResponse
)