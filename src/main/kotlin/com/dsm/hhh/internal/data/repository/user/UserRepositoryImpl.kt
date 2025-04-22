package com.dsm.hhh.internal.data.repository.user

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.data.repository.user.mapper.UserEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * UserRepositoryImpl - 사용자 도메인 리포지토리 구현체
 * <p>
 * MongoRepository를 한 번 랩핑한 클래스입니다.
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