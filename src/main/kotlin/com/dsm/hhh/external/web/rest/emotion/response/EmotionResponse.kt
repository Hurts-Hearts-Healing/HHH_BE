package com.dsm.hhh.external.web.rest.emotion.response

import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId


data class EmotionResponse(
    val emotion: Emotion
)

class EmotionListResponse(
    val emotionList: List<EmotionResponse>
)