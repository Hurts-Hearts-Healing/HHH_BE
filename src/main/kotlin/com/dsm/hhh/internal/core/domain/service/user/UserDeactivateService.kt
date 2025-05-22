package com.dsm.hhh.internal.core.domain.service.user

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.user.archive.ArchiveUserInternalDTO
import com.dsm.hhh.internal.core.usecase.user.UserDeactivateUseCase
import com.dsm.hhh.internal.data.repository.user.UserRepository
import com.dsm.hhh.internal.data.repository.user.archive.ArchiveUserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
private class UserDeactivateService(
    private val currentUser: CurrentUser,
    private val userRepository: UserRepository,
    private val archiveUserRepository: ArchiveUserRepository
) : UserDeactivateUseCase {

    override fun deactivateUser(): Mono<Void> {
        return currentUser.get()
            .flatMap { user ->
                val userId = user.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                val archiveUserDTO = ArchiveUserInternalDTO(user.email, listOf(userId))
                
                archiveUserRepository.findByEmail(user.email)
                    .flatMap { existingArchiveUser ->
                        archiveUserRepository.save(archiveUserDTO, existingArchiveUser)
                    }
                    .switchIfEmpty(
                        archiveUserRepository.save(archiveUserDTO)
                    )
                    .thenReturn(userId)
            }
            .flatMap { userId -> userRepository.deleteById(userId) }
    }

}