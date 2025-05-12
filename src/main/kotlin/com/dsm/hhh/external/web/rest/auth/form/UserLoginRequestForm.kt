package com.dsm.hhh.external.web.rest.auth.form

import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password

/**
 * UserLoginRequest - 사용자 로그인 요청 DTO
 * <p>
 * 클라이언트로부터 전달된 로그인 요청 데이터를 담는 DTO 클래스
 * </p>
 *
 * @property email 사용자 이메일
 * @property password 사용자 비밀번호
 *
 * @author Kim Seung Won
 * @since 2025-05-12
 * @version 1.0
 */
data class UserLoginRequestForm(
    val email: Email,
    val password: Password
)