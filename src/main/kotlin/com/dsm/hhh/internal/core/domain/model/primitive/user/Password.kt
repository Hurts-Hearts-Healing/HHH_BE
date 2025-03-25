package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Password - 사용자 비밀번호 값 객체
 * <p>
 * 비밀번호 복잡도 요구사항을 검증하는 값 객체입니다.
 * 다음 규칙을 검증합니다:
 * - 8~20자 길이
 * - 영문, 숫자, 특수문자 포함
 * - 공백 불허
 * </p>
 *
 * @property password 원본 비밀번호 문자열
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class Password(
    private val password: String
) {

    companion object {
        val letterRegex = ".*[A-Za-z].*".toRegex()
        val digitRegex = ".*\\d.*".toRegex()
        val specialCharRegex = ".*[!@#\$%^&*()\\-+=].*".toRegex()
        val spaceRegex = ".*\\s.*".toRegex()
    }

    init {
        AssertionUtils.assertArgumentNotEmpty(password, "비밀번호가 입력되지 않았습니다.")
        AssertionUtils.assertArgumentLength(password, 8, 20, "비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
        AssertionUtils.assertRegularExpression(password, letterRegex, "비밀번호에는 최소 1개의 영문자가 포함되어야 합니다.")
        AssertionUtils.assertRegularExpression(password, digitRegex, "비밀번호에는 최소 1개의 숫자가 포함되어야 합니다.")
        AssertionUtils.assertRegularExpression(password, specialCharRegex, "비밀번호에는 최소 1개의 특수문자 (!@#\$%^&*()-+=)가 포함되어야 합니다.")
        AssertionUtils.assertRegularExpression(password, spaceRegex, "비밀번호에는 공백을 포함할 수 없습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 비밀번호 문자열
     */
    fun value() = password

}