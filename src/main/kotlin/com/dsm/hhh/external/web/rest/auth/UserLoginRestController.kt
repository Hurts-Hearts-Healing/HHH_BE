package com.dsm.hhh.external.web.rest.auth

import com.dsm.hhh.external.web.rest.auth.form.UserLoginRequestForm
import com.dsm.hhh.external.web.rest.auth.response.UserLoginResponse
import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.internal.core.usecase.user.UserLoginUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * UserController - 사용자 관련 API 컨트롤러
 * <p>
 * 사용자 관련 HTTP 요청을 처리하는 REST 컨트롤러
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-08
 * @version 1.0
 */
@RestController
private class UserLoginRestController(
    private val userLoginUseCase: UserLoginUseCase
) {

    /**
     * 사용자 로그인 처리
     * @param request 로그인 요청 DTO
     * @return 로그인 응답 DTO
     */
    @PostMapping(RestApiSpec.AUTH_LOGIN)
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody userLoginRequestForm: UserLoginRequestForm): Mono<UserLoginResponse> {
        return userLoginUseCase.login(
            email = userLoginRequestForm.email,
            password = userLoginRequestForm.password
        ).map { token ->
            UserLoginResponse(token)
        }
    }

}