package com.dsm.hhh.internal.core.domain.service.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import com.dsm.hhh.internal.data.repository.mail.AuthMailRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap


@Service
private class AuthMailService(
    val authMailRepository: AuthMailRepository
): AuthMailUseCase {
    private val random = kotlin.random.Random

    override fun sendCodeToEmail(authMailDTO: AuthMailDTO): Mono<VerifyCodeDTO> {
        val verifyCode = (100000..999999).random(random).toString()
        val verifyCodeDTO = VerifyCodeDTO(authMailDTO.email, verifyCode)

        return authMailRepository.save(verifyCodeDTO)
    }

    override fun verifyCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean> {
        val storedCode = authMailRepository.findByEmail(verifyCodeDTO.email)

        return if (storedCode == verifyCodeDTO.verifyCode) {
            authMailRepository.delete(verifyCodeDTO.email)
            Mono.just(true)
        } else {
            Mono.just(false)
        }
    }
}