package com.dsm.hhh.external.web.rest.mail

import com.dsm.hhh.external.web.rest.mail.form.AuthMailRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/mail")
class AuthMailRestController(
    private val authMailUseCase: AuthMailUseCase
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody authMailDTO: AuthMailDTO): VerifyCodeDTO {
        return authMailUseCase.sendCodeToEmail(authMailDTO).block()!!
    }

    @PostMapping("/verify")
    fun verify(@RequestBody form: AuthMailRequestForm): Boolean {
        val verifyCodeDTO = com.dsm.hhh.external.web.rest.mail.mapper.AuthMailMapper.toInternalDTO(form)
        return authMailUseCase.verifyCode(verifyCodeDTO).block() ?: false
    }
}