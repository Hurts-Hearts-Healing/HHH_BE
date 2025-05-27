package com.dsm.hhh.internal.core.usecase.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import reactor.core.publisher.Mono

interface AuthMailUseCase {

    fun sendVerificationCodeToEmail(authMailDTO: AuthMailDTO): Mono<VerifyCodeDTO>

    fun verifyEmailCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean>

}