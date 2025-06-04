package com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis

import java.time.LocalDate

class EmotionAnalysisInternalDTO(
    val userId: String,

    val diaryId: String,

    val emotion: String,

    val createdAt: LocalDate?,

    ) {
    constructor(
        userId: String,
        diaryId: String,
        emotion: String,
    ) : this(userId, diaryId, emotion, null)
}