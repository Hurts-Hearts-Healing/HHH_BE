package com.dsm.hhh.external.web.rest.emotion.response

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion

data class EmotionResponse(
    val emotion: Emotion,
    val createdAt: CreatedAt
)

class EmotionListResponse(
    val emotionList: List<EmotionResponse>
)