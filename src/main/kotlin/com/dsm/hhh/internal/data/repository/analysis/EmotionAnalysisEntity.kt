package com.dsm.hhh.internal.data.repository.analysis

import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime


@Document(collection = CollectionSpec.EMOTION_ANALYSIS)
class EmotionAnalysisEntity private constructor(
    @Id
    val id: String? = null,

    val diaryId: String,

    val summary: String,

    val score: Int,

    val change: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(
        diaryId: String,
        summary: String,
        score: Int,
        change: String
    ) : this(
        id = null,
        diaryId = diaryId,
        summary = summary,
        score = score,
        change = change,
        createdAt = LocalDateTime.now()
    )
}