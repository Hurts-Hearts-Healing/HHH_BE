package com.dsm.hhh.internal.core.domain.model.primitive.mail

import com.dsm.hhh.internal.common.assertion.AssertionUtils


@JvmInline
value class VerifyCode(
    val verifyCode: String,
) {
    init {
        AssertionUtils.assertArgumentLength(verifyCode, 4,6,"인증번호는 6자리이어야 합니다.")
    }

    fun value() = verifyCode
}