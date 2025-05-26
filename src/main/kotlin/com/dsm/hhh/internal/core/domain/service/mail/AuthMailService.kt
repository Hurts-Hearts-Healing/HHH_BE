package com.dsm.hhh.internal.core.domain.service.mail

import com.dsm.hhh.internal.core.domain.component.mail.CompletedEmailVerificationComponent
import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.domain.model.primitive.mail.VerifyCode
import com.dsm.hhh.internal.core.usecase.mail.AuthMailUseCase
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
private class AuthMailService(
    private val javaMailSender: JavaMailSender,
    private val completedEmailVerificationComponent: CompletedEmailVerificationComponent
) : AuthMailUseCase {
    private val verificationStorage = ConcurrentHashMap<String, String>()

    override fun sendCodeToEmail(authMailDTO: AuthMailDTO): Mono<VerifyCodeDTO> {
        val verifyCode = VerifyCode()
        verificationStorage[authMailDTO.email.value()] = verifyCode.value()

        val message = SimpleMailMessage()
        message.setTo(authMailDTO.email.value())
        message.setSubject("이메일 인증 코드")
        message.setText("인증 코드는 ${verifyCode.value()} 입니다.")

        javaMailSender.send(message)

        return Mono.just(VerifyCodeDTO(authMailDTO.email, verifyCode))
    }


    override fun verifyCode(verifyCodeDTO: VerifyCodeDTO): Mono<Boolean> {
        val storedCode = verificationStorage[verifyCodeDTO.email.value()]

        if (storedCode == verifyCodeDTO.verifyCode.value()) {
            completedEmailVerificationComponent.saveCompletedEmail(verifyCodeDTO.email)

            return Mono.just(true)
        }

        return Mono.just(false)
    }

}