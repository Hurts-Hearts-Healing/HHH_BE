package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.external.security.PasswordEncoderUtils
import com.dsm.hhh.internal.common.assertion.AssertionUtils

/**
 * HashedPassword - 해시 처리된 비밀번호 값 객체
 * <p>
 * 안전한 저장을 위해 해시 처리된 비밀번호를 표현하는 값 객체입니다.
 * 원본 비밀번호와 달리 복잡도 검증은 수행하지 않습니다.
 * </p>
 *
 * @property hashedPassword 해시된 비밀번호 문자열
 *
 * @author Kim Seung Won
 * @since 2025-05-12
 * @version 1.1
 */
@JvmInline
value class HashedPassword(
    private val hashedPassword: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(hashedPassword, "비밀번호가 입력되지 않았습니다.")
    }

    companion object {
        fun fromPassword(password: Password): HashedPassword {
            return PasswordEncoderUtils.encode(password)
        }
    }

    /**
     * 내부 값을 반환합니다.
     * @return 해시된 비밀번호 문자열
     */
    fun value() = hashedPassword

}