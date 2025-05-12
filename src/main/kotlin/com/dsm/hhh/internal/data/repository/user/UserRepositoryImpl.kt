package com.dsm.hhh.internal.data.repository.user

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Birthday
import com.dsm.hhh.internal.core.domain.model.primitive.user.BreakupDate
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.EmotionStatus
import com.dsm.hhh.internal.core.domain.model.primitive.user.HashedPassword
import com.dsm.hhh.internal.core.domain.model.primitive.user.Name
import com.dsm.hhh.internal.core.domain.model.primitive.user.RegisteredAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDateTime

/**
 * UserRepositoryImpl - 사용자 도메인 리포지토리 구현체
 * <p>
 * UserMongoRepository를 활용하여 도메인 인터페이스를 구현합니다.
 * Entity와 DTO 간의 변환을 담당합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-25
 * @version 1.0
 */
@Component
class UserRepositoryImpl(
    private val userMongoRepository: UserMongoRepository
) : UserRepository {

    override fun save(userInternalDTO: UserInternalDTO): Mono<UserInternalDTO> {
        val entity = toEntity(userInternalDTO)
        return userMongoRepository.save(entity)
            .map { toDTO(it) }
    }

    override fun findByEmail(email: Email): Mono<UserInternalDTO> {
        return userMongoRepository.findByEmail(email.value())
            .map { toDTO(it) }
    }

    // Entity -> DTO 변환
    private fun toDTO(entity: UserEntity): UserInternalDTO {
        return UserInternalDTO(
            userId = entity.id?.let { UserId(it) },
            name = Name(entity.name),
            email = Email(entity.email),
            password = HashedPassword(entity.password),
            birthday = Birthday(entity.birthday),
            breakupDate = BreakupDate(entity.breakupDate),
            emotionStatus = EmotionStatus(entity.emotionStatus),
            registeredAt = entity.registeredAt?.let { RegisteredAt(it) }
        )
    }

    // DTO -> Entity 변환
    private fun toEntity(dto: UserInternalDTO): UserEntity {
        return if (dto.userId != null) {
            UserEntity(
                id = dto.userId.value(),
                name = dto.name.value(),
                email = dto.email.value(),
                password = dto.password.value(),
                birthday = dto.birthday.value(),
                breakupDate = dto.breakupDate.value(),
                emotionStatus = dto.emotionStatus.value(),
                registeredAt = dto.registeredAt?.value() ?: LocalDateTime.now()
            )
        } else {
            UserEntity(
                name = dto.name.value(),
                email = dto.email.value(),
                password = dto.password.value(),
                birthday = dto.birthday.value(),
                breakupDate = dto.breakupDate.value(),
                emotionStatus = dto.emotionStatus.value()
            )
        }
    }
}