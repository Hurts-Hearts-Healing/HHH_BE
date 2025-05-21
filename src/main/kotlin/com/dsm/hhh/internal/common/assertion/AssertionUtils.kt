package com.dsm.hhh.internal.common.assertion

import com.dsm.hhh.internal.common.exception.CustomExceptionFactory

/**
 * AssertionUtils - 도메인 유효성 검증 유틸리티 클래스
 * <p>
 * 도메인 객체의 불변 조건(invariant)을 검증하기 위한 메서드들을 제공합니다.
 * 도메인 객체의 검증을 위한 순수 유틸리티 클래스로, 스프링 빈으로 관리되지 않습니다.
 * 모든 검증 실패 시 IllegalArgumentException을 발생시킵니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-17
 * @version 1.1
 */
class AssertionUtils private constructor() {

    companion object {

        /**
         * 문자열이 null이거나 비어있지 않은지 검증합니다.
         * @param string 검증할 문자열
         * @param message 검증 실패 시 예외 메시지
         * @throws CustomExceptionFactory.badRequest 문자열이 null이거나 공백일 경우
         */
        fun assertArgumentNotEmpty(
            string: String?,
            message: String
        ) {
            if (string.isNullOrBlank()) {
                throw CustomExceptionFactory.badRequest("VALID_001", message)
            }
        }

        /**
         * 객체가 null이 아닌지 검증합니다.
         * @param obj 검증할 객체
         * @param message 검증 실패 시 예외 메시지
         * @throws CustomExceptionFactory.badRequest 객체가 null일 경우
         */
        fun assertArgumentNotNull(
            obj: Any?,
            message: String
        ) {
            if (obj == null) {
                throw CustomExceptionFactory.badRequest("VALID_002", message)
            }
        }

        /**
         * 숫자 값이 지정된 범위 내에 있는지 검증합니다.
         * @param value 검증할 숫자 값
         * @param minValue 허용 최소값 (포함)
         * @param maxValue 허용 최대값 (포함)
         * @param message 검증 실패 시 예외 메시지
         * @throws CustomExceptionFactory.badRequest 값이 범위를 벗어날 경우
         */
        fun assertArgumentValueInRange(
            value: Int,
            minValue: Int,
            maxValue: Int,
            message: String
        ) {
            if (value < minValue || value > maxValue) {
                throw CustomExceptionFactory.badRequest("VALID_003", message)
            }
        }

        /**
         * 문자열 길이가 지정된 범위 내에 있는지 검증합니다.
         * @param string 검증할 문자열
         * @param minLength 허용 최소 길이 (포함)
         * @param maxLength 허용 최대 길이 (포함)
         * @param message 검증 실패 시 예외 메시지
         * @throws CustomExceptionFactory.badRequest 문자열 길이가 범위를 벗어날 경우
         */
        fun assertArgumentLength(
            string: String,
            minLength: Int,
            maxLength: Int,
            message: String
        ) {
            if (string.length < minLength || string.length > maxLength) {
                throw CustomExceptionFactory.badRequest("VALID_004", message)
            }
        }

        /**
         * 문자열이 정규표현식과 일치하는지 검증합니다.
         * @param string 검증할 문자열
         * @param regex 검증에 사용할 정규표현식
         * @param message 검증 실패 시 예외 메시지
         * @throws CustomExceptionFactory.badRequest 문자열이 정규식과 일치하지 않을 경우
         */
        fun assertRegularExpression(
            string: String,
            regex: Regex,
            message: String
        ) {
            if (!string.matches(regex)) {
                throw CustomExceptionFactory.badRequest("VALID_005", message)
            }
        }

    }

}