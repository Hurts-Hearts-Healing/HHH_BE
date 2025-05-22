package com.dsm.hhh.internal.core.domain.model.primitive.mail

import com.dsm.hhh.internal.common.assertion.AssertionUtils


@JvmInline
value class VerifyCode private constructor(
    private val verifyCode: String,
) {

    init {
        AssertionUtils.assertArgumentLength(verifyCode, 4,6,"인증번호는 6자리이어야 합니다.")
    }

    fun value() = verifyCode

    constructor(): this(
        verifyCode = (100000..999999).random().toString()
    )

}