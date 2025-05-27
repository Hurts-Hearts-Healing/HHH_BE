package com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis

import java.time.LocalDate

class EmotionAnalysisInternalDTO(
    val userId: String?,

    val emotion: String,

    val createdAt: LocalDate?,

    val score: Double?
) {
    constructor(
        emotion: String,
    ) : this(null, emotion, null, null)
}