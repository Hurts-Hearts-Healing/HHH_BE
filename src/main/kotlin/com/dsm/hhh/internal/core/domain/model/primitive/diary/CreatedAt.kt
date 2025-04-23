package com.dsm.hhh.internal.core.domain.model.primitive.diary

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import java.time.LocalDate

@JvmInline
value class CreatedAt(
    private val createdAt: LocalDate
) {

    init {
        AssertionUtils.assertArgumentNotNull(createdAt, "일기가 작성된 날짜가 입력되지 않았습니다.")
    }

    fun value() = createdAt

}