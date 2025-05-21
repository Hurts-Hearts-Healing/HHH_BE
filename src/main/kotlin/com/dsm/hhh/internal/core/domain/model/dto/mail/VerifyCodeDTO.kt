package com.dsm.hhh.internal.core.domain.model.dto.mail

data class VerifyCodeDTO(
    val email: String,
    val verifyCode: String
)