package com.dsm.hhh.internal.core.domain.model.primitive.user

import com.dsm.hhh.internal.common.assertion.AssertionUtils
import com.fasterxml.jackson.annotation.JsonValue

/**
 * EmotionStatus - 사용자 감정 상태 값 객체
 * <p>
 * 사용자의 현재 감정 상태를 0~100 범위로 표현하는 값 객체입니다.
 * </p>
 *
 * @property emotionStatus 감정 상태 값 (0~100)
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@JvmInline
value class EmotionStatus(
    private val emotionStatus: Int
) {

    init {
        AssertionUtils.assertArgumentNotNull(emotionStatus, "현재 감정이 입력되지 않았습니다.")
        AssertionUtils.assertArgumentValueInRange(emotionStatus, 0, 100, "현재 감정은 0 ~ 100로 입력해주세요.")
    }

    /**
     * 내부 값을 반환합니다.
     * @return 감정 상태 정수 값
     */
    fun value() = emotionStatus

}