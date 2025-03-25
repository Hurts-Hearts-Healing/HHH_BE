package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue

/**
 * BreakupDate - 이별 일자 값 객체
 * <p>
 * 사용자의 마지막 이별 날짜를 표현하는 값 객체입니다.
 * </p>
 *
 * @property breakupDate 이별 날짜 문자열 (YYYY-MM-DD 형식 예상)
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class BreakupDate(
    private val breakupDate: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(breakupDate, "이별 날짜가 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 이별 날짜 문자열
     */
    fun value() = breakupDate

}