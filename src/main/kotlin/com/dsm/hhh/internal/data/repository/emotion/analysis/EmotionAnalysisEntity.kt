package com.dsm.hhh.internal.data.repository.emotion.analysis

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDate


@Document(collection = CollectionSpec.ANALYSIS)
class EmotionAnalysisEntity private constructor(
    @Id
    val id: String? = null,

    val userId: String,

    val diaryId: String,

    val emotion: String,

    @Field(targetType = FieldType.DATE_TIME)
    val createdAt: LocalDate,
) {
    constructor(
        userId: String,
        diaryId: String,
        emotion: String,
    ) : this(null, userId, diaryId, emotion, LocalDate.now())
}