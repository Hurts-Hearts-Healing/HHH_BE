package com.dsm.hhh.internal.core.domain.service.user

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.mail.CompletedEmailVerificationComponent
import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.usecase.user.UserRegisterUseCase
import com.dsm.hhh.internal.data.repository.user.UserRepository
import com.mongodb.MongoTimeoutException
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * UserRegisterService - 사용자 등록 서비스 구현체
 * <p>
 * 사용자 등록 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * UserRepository를 사용하여 데이터를 저장하고 예외 처리를 수행합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@Service
private class UserRegisterService(
    private val userRepository: UserRepository,
    private val completedEmailVerificationComponent: CompletedEmailVerificationComponent
): UserRegisterUseCase {

    /**
     * 사용자 정보를 등록합니다.
     * 저장 과정에서 발생할 수 있는 예외를 처리합니다.
     */
    override fun register(userInternalDTO: UserInternalDTO): Mono<Void> {
        return completedEmailVerificationComponent.isCompletedEmail(userInternalDTO.email)
            .flatMap {
                if (it) {
                    saveUser(userInternalDTO)
                } else {
                    Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_007))
                }
            }
    }

    private fun saveUser(userInternalDTO: UserInternalDTO): Mono<Void> {
        return userRepository.save(userInternalDTO)
            .onErrorResume {
                when (it) {
                    is DuplicateKeyException -> throw CustomExceptionFactory.conflict(ErrorCode.AUTH_004)
                    is MongoTimeoutException -> throw CustomExceptionFactory.serviceUnavailable(ErrorCode.INTERNAL_001)
                    else -> throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_002)
                }
            }
            .doOnSuccess { completedEmailVerificationComponent.deleteCompletedEmail(userInternalDTO.email) }
            .then()
    }

}