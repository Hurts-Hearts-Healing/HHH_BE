package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Email - 사용자 이메일 주소 값 객체
 * <p>
 * 사용자 식별을 위한 이메일 주소를 표현하는 값 객체입니다.
 * 시스템의 대체키로 사용됩니다.
 * </p>
 *
 * @property email 이메일 주소
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class Email(
    private val email: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(email, "이메일이 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 이메일 주소 문자열
     */
    fun value() = email

}