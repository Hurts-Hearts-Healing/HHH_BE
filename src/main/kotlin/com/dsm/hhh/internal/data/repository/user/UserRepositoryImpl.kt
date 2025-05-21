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
import com.dsm.hhh.internal.data.repository.user.mapper.UserEntityMapper
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
        val entity = UserEntityMapper.toEntity(userInternalDTO)
        return userMongoRepository.save(entity)
            .map(UserEntityMapper::toDTO)
    }

    override fun findByEmail(email: Email): Mono<UserInternalDTO> {
        return userMongoRepository.findByEmail(email.value())
            .map(UserEntityMapper::toDTO)

    }

    override fun findById(userId: UserId): Mono<UserInternalDTO> {
        return userMongoRepository.findById(userId.value())
            .map(UserEntityMapper::toDTO)
    }

}