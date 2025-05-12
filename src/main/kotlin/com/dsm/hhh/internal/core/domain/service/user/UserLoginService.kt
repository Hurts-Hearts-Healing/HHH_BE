package com.dsm.hhh.internal.core.domain.service.user

import com.dsm.hhh.external.security.PasswordEncoderUtils
import com.dsm.hhh.external.security.PasetoTokenUtils
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password
import com.dsm.hhh.internal.core.usecase.user.UserLoginUseCase
import com.dsm.hhh.internal.data.repository.user.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * UserLoginService - 사용자 로그인 서비스
 * <p>
 * 사용자 인증 및 토큰 발행을 처리하는 도메인 서비스
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-12
 * @version 1.0
 */
@Service
private class UserLoginService(
    private val userRepository: UserRepository,
    private val pasetoTokenUtils: PasetoTokenUtils
): UserLoginUseCase {

    /**
     * 사용자 로그인 처리
     * @param email 이메일
     * @param password 비밀번호
     * @return 생성된 토큰
     */
    override fun login(email: Email, password: Password): Mono<String> {

        return userRepository.findByEmail(email)
            .switchIfEmpty(Mono.error(RuntimeException("사용자를 찾을 수 없습니다.")))
            .flatMap { user ->
                if (PasswordEncoderUtils.matches(password, user.password)) {
                    pasetoTokenUtils.generateToken(
                        userId = user.userId?.value()
                            ?: return@flatMap Mono.error(RuntimeException("사용자 ID를 찾을 수 없습니다."))
                    )
                } else {
                    Mono.error(RuntimeException("비밀번호가 일치하지 않습니다."))
                }
            }
    }
}