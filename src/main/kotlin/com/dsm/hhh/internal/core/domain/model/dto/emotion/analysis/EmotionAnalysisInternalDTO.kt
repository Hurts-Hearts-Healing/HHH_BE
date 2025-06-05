package com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt

class EmotionAnalysisInternalDTO(
    val userId: String,

    val diaryId: String,

    val emotion: String,

    val createdAt: CreatedAt?,

    ) {
    constructor(
        userId: String,
        diaryId: String,
        emotion: String,
    ) : this(userId, diaryId, emotion, null)
}