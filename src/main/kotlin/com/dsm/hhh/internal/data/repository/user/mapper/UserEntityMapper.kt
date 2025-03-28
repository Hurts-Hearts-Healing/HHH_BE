package com.dsm.hhh.internal.data.repository.user.mapper

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Birthday
import com.dsm.hhh.internal.core.domain.model.primitive.user.BreakupDate
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.EmotionStatus
import com.dsm.hhh.internal.core.domain.model.primitive.user.HashedPassword
import com.dsm.hhh.internal.core.domain.model.primitive.user.Name
import com.dsm.hhh.internal.core.domain.model.primitive.user.RegisteredAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.user.UserEntity

/**
 * UserEntityMapper - DTO와 엔티티 변환 매퍼
 * <p>
 * UserInternalDTO와 UserEntity 간의 변환을 담당하는 매퍼 클래스입니다.
 * 컴패니언 객체로 구현되어 인스턴스 생성 없이 사용 가능합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
class UserEntityMapper {

    companion object {

        /**
         * DTO를 엔티티로 변환합니다.
         * @param userInternalDTO 변환할 DTO 객체
         * @return 변환된 UserEntity 객체
         */
        fun toEntity(userInternalDTO: UserInternalDTO): UserEntity {
            return UserEntity(
                name = userInternalDTO.name.value(),
                email = userInternalDTO.email.value(),
                password = userInternalDTO.password.value(),
                birthday = userInternalDTO.birthday.value(),
                breakupDate = userInternalDTO.breakupDate.value(),
                emotionStatus = userInternalDTO.emotionStatus.value()
            )
        }

        /**
         * 엔티티를 DTO로 변환합니다.
         * @param userEntity 변환할 엔티티 객체
         * @return 변환된 UserInternalDTO 객체
         */
        fun toDTO(userEntity: UserEntity): UserInternalDTO {
            return UserInternalDTO(
                userId = UserId(userEntity.id!!),
                name = Name(userEntity.name),
                email = Email(userEntity.email),
                password = HashedPassword(userEntity.password),
                birthday = Birthday(userEntity.birthday),
                breakupDate = BreakupDate(userEntity.breakupDate),
                emotionStatus = EmotionStatus(userEntity.emotionStatus),
                registeredAt = RegisteredAt(userEntity.registeredAt)
            )
        }

    }

}