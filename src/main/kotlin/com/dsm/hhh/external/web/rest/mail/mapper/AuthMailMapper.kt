package com.dsm.hhh.external.web.rest.mail.mapper

import com.dsm.hhh.external.web.rest.mail.form.AuthMailRequestForm
import com.dsm.hhh.external.web.rest.mail.form.VerificationRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.mail.AuthMailDTO
import com.dsm.hhh.internal.core.domain.model.dto.mail.VerifyCodeDTO

class AuthMailMapper {

    companion object {

        fun toAuthMailDTO(form: AuthMailRequestForm): AuthMailDTO {
            return AuthMailDTO(
                email = form.email
            )
        }

        fun toVerifyCodeDTO(verificationRequestForm: VerificationRequestForm): VerifyCodeDTO {
            return VerifyCodeDTO(
                email = verificationRequestForm.email,
                verifyCode = verificationRequestForm.verifyCode
            )
        }

    }

}