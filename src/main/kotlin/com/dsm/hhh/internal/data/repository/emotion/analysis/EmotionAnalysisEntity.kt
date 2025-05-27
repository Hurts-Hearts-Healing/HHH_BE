package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document(collation = CollectionSpec.ANALYSIS)
class EmotionAnalysisEntity private constructor(
    @Id
    val id: String?,

    val userId: String,

    val emotion: String,

    val createdAt: LocalDate,

    val score: Double?
) {
    constructor(
        userId: String,
        emotion: String,
        score: Double?,
    ) : this(null, userId, emotion, LocalDate.now(), score)
}