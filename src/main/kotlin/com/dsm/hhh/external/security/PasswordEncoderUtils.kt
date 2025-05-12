package com.dsm.hhh.external.security

import com.dsm.hhh.internal.core.domain.model.primitive.user.HashedPassword
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * PasswordEncoderUtil - 비밀번호 인코딩 유틸리티
 * <p>
 * 비밀번호 해싱을 위한 순수 유틸리티 클래스로, 스프링 빈으로 관리되지 않습니다.
 * BCrypt 알고리즘을 사용한 비밀번호 인코딩/매칭 기능을 제공합니다.
 * </p>
 *
 * @since 2025-05-12
 */
class PasswordEncoderUtils private constructor() {

    companion object {
        private val encoder = BCryptPasswordEncoder()

        /**
         * 비밀번호를 BCrypt로 해싱합니다.
         * @param rawPassword 원본 비밀번호
         * @return 해시된 비밀번호
         */
        fun encode(rawPassword: Password): HashedPassword {
            return HashedPassword(encoder.encode(rawPassword.value()))
        }

        /**
         * 원본 비밀번호와 해시된 비밀번호가 일치하는지 검증합니다.
         * @param rawPassword 검증할 원본 비밀번호
         * @param encodedPassword 저장된 해시된 비밀번호
         * @return 일치 여부
         */
        fun matches(
            rawPassword: Password,
            encodedPassword: HashedPassword
        ): Boolean {
            return encoder.matches(rawPassword.value(), encodedPassword.value())
        }
    }

}