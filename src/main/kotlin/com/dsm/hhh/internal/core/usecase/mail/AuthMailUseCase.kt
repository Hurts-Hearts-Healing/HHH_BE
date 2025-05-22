package com.dsm.hhh.internal.core.usecase.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import reactor.core.publisher.Mono

interface AuthMailUseCase {

    fun sendCodeToEmail(authMailDTO: AuthMailDTO): Mono<VerifyCodeDTO>

    fun verifyCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean>

}