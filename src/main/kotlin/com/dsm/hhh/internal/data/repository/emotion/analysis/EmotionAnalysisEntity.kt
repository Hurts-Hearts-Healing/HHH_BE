package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate


@Document(collation = CollectionSpec.ANALYSIS)
class EmotionAnalysisEntity private constructor(
    @Id
    val id: String? = null,

    val userId: String,

    val diaryId: String,

    val emotion: String,

    val createdAt: LocalDate?,
) {
    constructor(
        userId: String,
        diaryId: String,
        emotion: String,
        createdAt: LocalDate?
    ) : this(null, userId, diaryId, emotion, createdAt)
}