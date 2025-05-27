package com.dsm.hhh.external.web.rest.emotion.emoji.response

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion

class EmotionEmojiResponse(
    val emotion: Emotion,
    val createdAt: CreatedAt
)

class EmotionEmojiListResponse(
    val emotionList: List<EmotionEmojiResponse>
)