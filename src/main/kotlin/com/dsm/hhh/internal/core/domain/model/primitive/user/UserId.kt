package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils

/**
 * UserId - 사용자 식별자 값 객체
 * <p>
 * 시스템 내에서 사용자를 고유하게 식별하는 ID를 타입 안전하게 다루기 위한 값 객체입니다.
 * @JsonValue 어노테이션으로 직렬화 시 문자열 값으로 변환됩니다.
 * </p>
 *
 * @property userId 사용자 고유 식별자 (ObjectId)
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class UserId(
    private val userId: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(userId, "유저 아이디가 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 사용자 ID 문자열
     */
    fun value() = userId

}