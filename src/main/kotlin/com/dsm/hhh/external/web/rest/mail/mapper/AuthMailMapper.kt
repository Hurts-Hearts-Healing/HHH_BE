package com.dsm.hhh.external.web.rest.mail.mapper

import com.dsm.hhh.external.web.rest.mail.form.AuthMailRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO

class AuthMailMapper {
    companion object {
        fun toInternalDTO(form: AuthMailRequestForm): VerifyCodeDTO {
            return VerifyCodeDTO(
                email = form.email,
                verifyCode = form.verifyCode
            )
        }
    }
}