package com.dsm.hhh.external.web.rest.emotion.analysis.response

import java.time.LocalDate

class EmotionAnalysisResponse(
    val userId: String,
    val diaryId: String,
    val emotion: String,
    val createdAt: LocalDate?
)

class EmotionAnalysisGroupResponse(
    val analysis : EmotionAnalysisResponse
)