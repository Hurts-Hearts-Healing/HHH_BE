package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils

/**
 * Name - 사용자 실명 값 객체
 * <p>
 * 사용자의 실명을 표현하는 값 객체로, 비어있지 않음을 보장합니다.
 * </p>
 *
 * @property name 사용자 실명
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class Name(
    private val name: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(name, "이름이 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 사용자 이름 문자열
     */
    fun value() = name

}