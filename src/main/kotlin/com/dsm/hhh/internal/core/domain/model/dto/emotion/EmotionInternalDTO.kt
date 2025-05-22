package com.dsm.hhh.internal.core.domain.model.dto.emotion

import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId

data class EmotionInternalDTO(
    var userId: UserId?,
    val emotion: Emotion,
) {

    constructor(emotion: Emotion): this(null, emotion)

}