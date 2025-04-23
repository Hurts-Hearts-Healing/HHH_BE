package com.dsm.hhh.internal.core.domain.model.primitive.diary

import com.dsm.hhh.internal.common.assertion.AssertionUtils

/**
 * DiaryId - 감정 일기 식별자 값 객체
 * <p>
 * 시스템 내에서 사용자를 고유하게 식별하는 ID를 타입 안전하게 다루기 위한 값 객체입니다.
 * @JsonValue 어노테이션으로 직렬화 시 문자열 값으로 변환됩니다.
 * </p>
 *
 * @property diaryId 사용자 고유 식별자 (ObjectId)
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
@JvmInline
value class DiaryId(
    private val diaryId: String
) {

    init {
        AssertionUtils.assertArgumentNotEmpty(diaryId, "감정 일기 아이디가 입력되지 않았습니다.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 감정 일기 ID 문자열
     */
    fun value() = diaryId

}