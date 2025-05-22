package com.dsm.hhh.internal.core.domain.model.dto.mail

import com.dsm.hhh.internal.core.domain.model.primitive.mail.VerifyCode
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email

data class VerifyCodeDTO(
    val email: Email,
    val verifyCode: VerifyCode
)