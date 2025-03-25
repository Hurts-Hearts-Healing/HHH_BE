package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDateTime

/**
 * RegisteredAt - 사용자 가입 일시 값 객체
 * <p>
 * 사용자 가입 일시를 명시적으로 표현하기 위한 값 객체입니다.
 * @JsonValue 어노테이션으로 직렬화 시 ISO-8601 형식 문자열로 변환됩니다.
 * </p>
 *
 * @property registeredAt 가입 일시 (LocalDateTime)
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class RegisteredAt(
    private val registeredAt: LocalDateTime
) {


    init {
        AssertionUtils.assertArgumentNotNull(registeredAt, "가입 날짜가 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 가입 일시 객체
     */
    fun value() = registeredAt

}