package com.dsm.hhh.internal.data.repository.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Repository
class AuthMailRepositoryImpl(
): AuthMailRepository {
    private val database: ConcurrentHashMap<String, String> = ConcurrentHashMap()

    override fun save(verifyCodeDTO: VerifyCodeDTO): Mono<VerifyCodeDTO> {
        database[verifyCodeDTO.email.toString()] = verifyCodeDTO.verifyCode.toString()
        return Mono.just(verifyCodeDTO)
    }

    override fun findByEmail(email: String): String? {
        return database[email]
    }

    override fun delete(email: String) {
        database.remove(email)
    }
}