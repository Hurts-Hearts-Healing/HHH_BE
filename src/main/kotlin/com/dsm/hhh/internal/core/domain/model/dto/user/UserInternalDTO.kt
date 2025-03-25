package com.dsm.hhh.internal.core.domain.model.dto.user

import com.dsm.hhh.internal.core.domain.model.primitive.user.Birthday
import com.dsm.hhh.internal.core.domain.model.primitive.user.BreakupDate
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.EmotionStatus
import com.dsm.hhh.internal.core.domain.model.primitive.user.HashedPassword
import com.dsm.hhh.internal.core.domain.model.primitive.user.Name
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password
import com.dsm.hhh.internal.core.domain.model.primitive.user.RegisteredAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId

/**
 * UserInternalDTO - 계층별 데이터 변환을 위한 사용자 정보 도메인 데이터 클래스
 * <p>
 *
 * @property id MongoDB에서 자동 생성되는 고유 식별자. 형식은 ObjectId
 * @property email 사용자 이메일 주소 (unique 인덱스 적용)
 * @property password 비밀번호
 * @property name 사용자 실명
 * @property birthday 사용자 생년월일
 * @property breakupDate 마지막 이별일
 * @property emotionStatus 사용자 가입일의 감정 상태 (단위: %).
 * @property registeredAt 사용자 가입일
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
data class UserInternalDTO(
    val userId: UserId?,

    val name: Name,
    val email: Email,
    val password: HashedPassword,

    val birthday: Birthday,

    val breakupDate: BreakupDate,
    val emotionStatus: EmotionStatus,

    val registeredAt: RegisteredAt?
) {

    constructor(
        name: Name,
        email: Email,
        password: HashedPassword,
        birthday: Birthday,
        breakupDate: BreakupDate,
        emotionStatus: EmotionStatus
    ): this(null, name, email, password, birthday, breakupDate, emotionStatus, null)

}