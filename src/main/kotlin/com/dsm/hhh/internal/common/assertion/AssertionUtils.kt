package com.dsm.hhh.internal.common.assertion

/**
 * AssertionUtils - 도메인 유효성 검증 유틸리티 클래스
 * <p>
 * 도메인 객체의 불변 조건(invariant)을 검증하기 위한 메서드들을 제공합니다.
 * 도메인 객체의 검증을 위한 순수 유틸리티 클래스로, 스프링 빈으로 관리되지 않습니다.
 * 모든 검증 실패 시 IllegalArgumentException을 발생시킵니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
class AssertionUtils private constructor() {

    companion object {
        /**
         * 문자열이 null이거나 비어있지 않은지 검증합니다.
         * @param string 검증할 문자열
         * @param message 검증 실패 시 예외 메시지
         * @throws IllegalArgumentException 문자열이 null이거나 공백일 경우
         */
        fun assertArgumentNotEmpty(
            string: String?,
            message: String
        ) {
            if (string.isNullOrBlank()) {
                throw IllegalArgumentException(message) // TODO: 커스텀 예외로 교체 가능
            }
        }

        /**
         * 객체가 null이 아닌지 검증합니다.
         * @param obj 검증할 객체
         * @param message 검증 실패 시 예외 메시지
         * @throws IllegalArgumentException 객체가 null일 경우
         */
        fun assertArgumentNotNull(
            obj: Any?,
            message: String
        ) {
            if (obj == null) {
                throw IllegalArgumentException(message) // TODO: 커스텀 예외로 교체 가능
            }
        }

        /**
         * 숫자 값이 지정된 범위 내에 있는지 검증합니다.
         * @param value 검증할 숫자 값
         * @param minValue 허용 최소값 (포함)
         * @param maxValue 허용 최대값 (포함)
         * @param message 검증 실패 시 예외 메시지
         * @throws IllegalArgumentException 값이 범위를 벗어날 경우
         */
        fun assertArgumentValueInRange(
            value: Int,
            minValue: Int,
            maxValue: Int,
            message: String
        ) {
            if (value < minValue || value > maxValue) {
                throw IllegalArgumentException(message) // TODO: 예외 처리 개선 필요
            }
        }

        /**
         * 문자열 길이가 지정된 범위 내에 있는지 검증합니다.
         * @param string 검증할 문자열
         * @param minLength 허용 최소 길이 (포함)
         * @param maxLength 허용 최대 길이 (포함)
         * @param message 검증 실패 시 예외 메시지
         * @throws IllegalArgumentException 문자열 길이가 범위를 벗어날 경우
         */
        fun assertArgumentLength(
            string: String,
            minLength: Int,
            maxLength: Int,
            message: String
        ) {
            if (string.length < minLength || string.length > maxLength) {
                throw IllegalArgumentException(message) // TODO: 예외 처리 개선 필요
            }
        }

        /**
         * 문자열이 정규표현식과 일치하는지 검증합니다.
         * @param string 검증할 문자열
         * @param regex 검증에 사용할 정규표현식
         * @param message 검증 실패 시 예외 메시지
         * @throws IllegalArgumentException 문자열이 정규식과 일치하지 않을 경우
         */
        fun assertRegularExpression(
            string: String,
            regex: Regex,
            message: String
        ) {
            if (!string.matches(regex)) {
                throw IllegalArgumentException(message) // TODO: 예외 처리 개선 필요
            }
        }
    }

    /**
     * 숫자가 지정된 자릿수를 만족하는지 검증합니다.
     * @param number 검증할 숫자
     * @param digitLength 요구되는 자릿수
     * @param message 검증 실패 시 예외 메시지
     * @throws IllegalArgumentException 숫자가 요구된 자릿수를 만족하지 않을 경우
     */
    fun assertDigitLength(
        number: String,
        digitLength: Int,
        message: String
    ) {
        if (number.length != digitLength) {
            throw IllegalArgumentException(message)
        }
    }

}