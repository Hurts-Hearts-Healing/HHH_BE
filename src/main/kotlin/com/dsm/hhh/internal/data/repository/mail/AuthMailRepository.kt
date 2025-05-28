package com.dsm.hhh.internal.data.repository.mail

import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import reactor.core.publisher.Mono

interface AuthMailRepository {
    fun save(verifyCodeDTO: VerifyCodeDTO): Mono<VerifyCodeDTO>

    fun findByEmail(email: String): String?

    fun delete(email: String)
}