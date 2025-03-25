package com.dsm.hhh.internal.data.repository.user

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.data.repository.user.mapper.UserEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * UserRegisterUseCase - 사용자 회원가입 요청 양식
 * <p>
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@Repository
class UserRepositoryImpl(
    val userMongoRepository: UserMongoRepository
): UserRepository {

    override fun save(userInternalDTO: UserInternalDTO): Mono<UserInternalDTO> {
        val userEntity = UserEntityMapper.toEntity(userInternalDTO)
        return userMongoRepository.save(userEntity)
            .map { UserEntityMapper.toDTO(it) };
    }

}