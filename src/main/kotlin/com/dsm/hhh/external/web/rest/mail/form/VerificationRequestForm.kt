package com.dsm.hhh.external.web.rest.mail.form

import com.dsm.hhh.internal.core.domain.model.primitive.mail.VerifyCode
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email

class VerificationRequestForm(
    val email: Email,
    val verifyCode: VerifyCode
)