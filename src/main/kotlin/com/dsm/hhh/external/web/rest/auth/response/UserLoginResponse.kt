package com.dsm.hhh.external.web.rest.auth.response

/**
 * UserLoginResponse - 사용자 로그인 응답 DTO
 * <p>
 * 로그인 성공 시 클라이언트에게 전달되는 데이터를 담는 DTO 클래스
 * </p>
 *
 * @property token Paseto 토큰
 *
 * @author Kim Seung Won
 * @since 2025-05-12
 * @version 1.0
 */
class UserLoginResponse(
    val token: String
)