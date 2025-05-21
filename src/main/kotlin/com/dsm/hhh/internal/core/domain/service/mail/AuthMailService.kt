package com.dsm.hhh.internal.core.domain.service.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap


@Service
private class AuthMailService(
    private val javaMailSender: JavaMailSender
) : AuthMailUseCase {
    private val verificationStorage = ConcurrentHashMap<String, String>()

    override fun sendCodeToEmail(authMailDTO: AuthMailDTO): Mono<VerifyCodeDTO> {
        val verifyCode = (100000..999999).random().toString()
        verificationStorage[authMailDTO.email] = verifyCode

        val message = SimpleMailMessage()
        message.setTo(authMailDTO.email)
        message.setSubject("이메일 인증 코드")
        message.setText("인증 코드는 $verifyCode 입니다.")
        javaMailSender.send(message)

        return Mono.just(VerifyCodeDTO(authMailDTO.email, verifyCode))
    }


    override fun verifyCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean> {
        val storedCode = verificationStorage[verifyCodeDTO.email]
        val isMatch = storedCode == verifyCodeDTO.verifyCode
        return Mono.just(isMatch)
    }
}