package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Birthday - 생년월일 값 객체
 * <p>
 * 사용자의 생년월일을 표현하는 값 객체입니다.
 * </p>
 *
 * @property birthday 생일 문자열 (YYYY-MM-DD 형식 예상)
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class Birthday(
    private val birthday: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(birthday, "생일이 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 생일 문자열
     */
    fun value() = birthday

}