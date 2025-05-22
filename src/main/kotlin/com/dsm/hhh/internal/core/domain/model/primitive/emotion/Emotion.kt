package com.dsm.hhh.internal.core.domain.model.primitive.emotion

import com.dsm.hhh.internal.common.assertion.AssertionUtils

@JvmInline
value class Emotion(
    private val emotion: String
) {
    init {
        AssertionUtils.assertArgumentNotEmpty(emotion, "감정이 입력되지 않았습니다.")
    }

    fun value() = emotion
}