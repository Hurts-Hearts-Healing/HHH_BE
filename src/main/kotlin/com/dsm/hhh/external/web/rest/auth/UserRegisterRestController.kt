package com.dsm.hhh.external.web.rest.auth

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.auth.form.UserRegisterRequestForm
import com.dsm.hhh.external.web.rest.auth.mapper.UserRegisterMapper
import com.dsm.hhh.internal.core.usecase.user.UserRegisterUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * UserRegisterRestController - 사용자 회원가입 API 컨트롤러
 * <p>
 * 클라이언트로부터 회원가입 요청을 받아 처리하는 REST 컨트롤러입니다.
 * Project Reactor 기반의 비동기 처리를 지원하며, 성공 시 HTTP 201(Created) 상태 코드를 반환합니다.
 * </p>
 *
 * <p>
 * 주요 역할:
 * - 회원가입 요청 데이터 검증 (Form 객체 수준)
 * - DTO 변환을 통해 내부 계층과 통신
 * - 적절한 HTTP 상태 코드 반환
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@RestController
private class UserRegisterRestController(
    private val userRegisterUseCase: UserRegisterUseCase
) {

    /**
     * 사용자 회원가입 API 엔드포인트
     * @param requestForm 회원가입 요청 데이터
     * @return Mono<Void> - 처리 완료 시그널 (내용 없음)
     *
     * @HTTP 201 - 회원가입 성공
     */
    @PostMapping(RestApiSpec.AUTH_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody userRegisterRequestForm: UserRegisterRequestForm): Mono<Void> {
        val userInternalDTO = UserRegisterMapper.toInternalDTO(userRegisterRequestForm)

        return userRegisterUseCase.register(userInternalDTO)
    }

}