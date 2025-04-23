package com.dsm.hhh.internal.core.domain.model.primitive.diary

import com.dsm.hhh.internal.common.assertion.AssertionUtils

@JvmInline
value class Note(
    private val note: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(note, "일기 내용이 입력되지 않았습니다.")
    }

    fun value() = note

}