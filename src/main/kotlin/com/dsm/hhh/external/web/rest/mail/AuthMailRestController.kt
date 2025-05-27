package com.dsm.hhh.external.web.rest.mail

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.mail.form.AuthMailRequestForm
import com.dsm.hhh.external.web.rest.mail.form.VerificationRequestForm
import com.dsm.hhh.external.web.rest.mail.mapper.AuthMailMapper
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class AuthMailRestController(
    private val authMailUseCase: AuthMailUseCase
) {

    @PostMapping(RestApiSpec.MAIL_SEND)
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody authMailRequestForm: AuthMailRequestForm): Mono<Void> {
        val authMailDTO = AuthMailMapper.toAuthMailDTO(authMailRequestForm)

        return authMailUseCase.sendVerificationCodeToEmail(authMailDTO)
            .then()
    }

    @PostMapping(RestApiSpec.MAIL_VERIFY)
    @ResponseStatus(HttpStatus.OK)
    fun verify(@RequestBody verificationRequestForm: VerificationRequestForm): Mono<Boolean> {
        val verifyCodeDTO = AuthMailMapper.toVerifyCodeDTO(verificationRequestForm)

        return authMailUseCase.verifyEmailCode(verifyCodeDTO)
    }

}