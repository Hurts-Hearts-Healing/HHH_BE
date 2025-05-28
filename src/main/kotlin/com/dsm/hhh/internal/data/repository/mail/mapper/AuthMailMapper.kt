package com.dsm.hhh.internal.data.repository.mail.mapper

import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO
import com.dsm.hhh.internal.core.domain.model.primitive.mail.VerifyCode

class AuthMailMapper {
    companion object {
        fun toDto(email: String, verifyCode: String): VerifyCodeDTO {
            return VerifyCodeDTO(email, verifyCode)
        }

        fun toDomain(dto: VerifyCodeDTO): VerifyCode {
            return VerifyCode(dto.verifyCode)
        }
    }
}