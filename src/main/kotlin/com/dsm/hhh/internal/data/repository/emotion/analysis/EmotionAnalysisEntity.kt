package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import com.dsm.hhh.internal.data.repository.CollectionSpec
import java.time.LocalDate


@Document(collection = CollectionSpec.ANALYSIS)
class EmotionAnalysisEntity private constructor(
    @Id
    val id: String? = null,

    val userId: String,

    val diaryId: String,

    val emotion: String,

    val createdAt: CreatedAt?,
) {
    constructor(
        userId: String,
        diaryId: String,
        emotion: String,
        createdAt: CreatedAt?
    ) : this(null, userId, diaryId, emotion, createdAt)
}