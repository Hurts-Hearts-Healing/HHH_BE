package com.dsm.hhh.external.web.rest.auth.mapper

import com.dsm.hhh.external.web.rest.auth.form.UserRegisterRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.HashedPassword

/**
 * UserRegisterMapper - 회원가입 데이터 변환 매퍼
 * <p>
 * 웹 계층의 회원가입 요청 폼(UserRegisterRequestForm)을
 * 도메인 계층에서 사용하는 DTO(UserInternalDTO)로 변환하는 매퍼 클래스입니다.
 * 컴패니언 객체로 구현되어 인스턴스 생성 없이 사용 가능합니다.
 * </p>
 *
 * <p>
 * 변환 규칙:
 * - 요청 폼의 필드들을 내부 DTO의 해당 필드들로 1:1 매핑
 * - 필드 검증은 DTO 생성자에서 수행
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
class UserRegisterMapper private constructor() {

    companion object {

        /**
         * 회원가입 요청 폼을 내부 DTO로 변환
         * @param form 클라이언트로부터 받은 회원가입 요청 데이터
         * @return 도메인 계층에서 사용할 UserInternalDTO
         */
        fun toInternalDTO(form: UserRegisterRequestForm): UserInternalDTO {
            return UserInternalDTO(
                name = form.name,
                email = form.email,
                password = HashedPassword.fromPassword(form.password),
                birthday = form.birthday,
                breakupDate = form.breakupDate,
                emotionStatus = form.emotionStatus
            )
        }

    }

}