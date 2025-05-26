package com.dsm.hhh.internal.core.domain.model.dto.emotion

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId

data class EmotionInternalDTO(
    var userId: UserId?,
    val emotion: Emotion,
    val createdAt: CreatedAt?
) {

    constructor(emotion: Emotion): this(null, emotion, null)

}