package com.dsm.hhh.internal.data.repository.emotion.emoji.result

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class EmotionAggregationResult(
    val emotion: String,
    val count: Int,
    val date: String? = null
)

data class EmotionDailyAggregationResult(
    @Id
    val id: EmotionGroupId,
    val count: Int
)

data class EmotionGroupId(
    val createdAt: LocalDate,
    val emotion: String
)

data class WeeklyEmotionAggregationResult(
    val emotion: String,
    val count: Int,
    val weekStart: LocalDate?
)