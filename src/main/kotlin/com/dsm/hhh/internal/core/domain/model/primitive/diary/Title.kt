package com.dsm.hhh.internal.core.domain.model.primitive.diary

import com.dsm.hhh.internal.common.assertion.AssertionUtils

/**
 * Title - 감정 일기 제목 값 객체
 * <p>
 * 시스템 내에서 사용자를 고유하게 식별하는 ID를 타입 안전하게 다루기 위한 값 객체입니다.
 * @JsonValue 어노테이션으로 직렬화 시 문자열 값으로 변환됩니다.
 * </p>
 *
 * @property title 사용자 고유 식별자 (ObjectId)
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
@JvmInline
value class Title(
    private val title: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(title, "감정 일기의 제목이 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 감정 제목 문자열
     */
    fun value() = title

}