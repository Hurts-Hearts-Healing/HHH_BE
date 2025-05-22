package com.dsm.hhh.external.web.rest.mail

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class AuthMailRestController(
    private val authMailUseCase: AuthMailUseCase
) {

    @PostMapping(RestApiSpec.MAIL_SEND)
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody authMailDTO: AuthMailDTO): Mono<Void> {
        return authMailUseCase.sendCodeToEmail(authMailDTO).then()
    }

    @PostMapping(RestApiSpec.MAIL_VERIFY)
    @ResponseStatus(HttpStatus.OK)
    fun verify(@RequestBody verifyCodeDTO: VerifyCodeDTO): Mono<Boolean> {
        return authMailUseCase.verifyCode(verifyCodeDTO)
    }

}