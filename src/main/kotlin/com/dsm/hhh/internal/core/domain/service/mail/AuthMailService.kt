package com.dsm.hhh.internal.core.domain.service.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.usecase.AuthMailUseCase
import com.dsm.hhh.internal.data.repository.mail.AuthMailRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap


@Service
private class AuthMailService(
    val authMailRepository: AuthMailRepository
): AuthMailUseCase {
    private val random = kotlin.random.Random
    private val verificationStorage = ConcurrentHashMap<String, String>()

    override fun sendCodeToEmail(authMailDTO: AuthMailDTO): VerifyCodeDTO {
        val verifyCode = (100000..999999).random(random).toString()
        verificationStorage[authMailDTO.email] = verifyCode
        return VerifyCodeDTO(authMailDTO.email, verifyCode)
    }

    override fun verifyCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean> {
        val storedCode = verificationStorage[verifyCodeDTO.email]
        val isMatch = storedCode == verifyCodeDTO.verifyCode
        return Mono.just(isMatch)
    }
}