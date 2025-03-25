package com.dsm.hhh.external.web.rest.auth.form

import com.dsm.hhh.internal.core.domain.model.primitive.user.Birthday
import com.dsm.hhh.internal.core.domain.model.primitive.user.BreakupDate
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.EmotionStatus
import com.dsm.hhh.internal.core.domain.model.primitive.user.Name
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password

/**
 * UserRegisterRequestForm - 사용자 회원가입 요청 양식
 * <p>
 *
 * @property email 사용자 이메일 주소
 * @property password 비밀번호
 * @property name 사용자 실명
 * @property birthday 사용자 생년월일
 * @property breakupDate 마지막 이별일
 * @property emotionStatus 사용자 가입일의 감정 상태 (단위: %).
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
class UserRegisterRequestForm(
    val name: Name,
    val email: Email,
    val password: Password,

    val birthday: Birthday,

    val breakupDate: BreakupDate,
    val emotionStatus: EmotionStatus
) {

}