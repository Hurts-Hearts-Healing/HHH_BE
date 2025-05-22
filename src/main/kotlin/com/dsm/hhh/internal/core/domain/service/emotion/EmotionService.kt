package com.dsm.hhh.internal.core.domain.service.emotion

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.usecase.emotion.EmotionUseCase
import com.dsm.hhh.internal.data.repository.emotion.EmotionRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
private class EmotionRegisterService(
    private val emotionRepository: EmotionRepository,
    private val currentUser: CurrentUser
) : EmotionUseCase {
    override fun register(emotionInternalDTO: EmotionInternalDTO): Mono<Void> {
        return emotionRepository.save(emotionInternalDTO)
            .onErrorResume {
                when (it) {
                    is DuplicateKeyException -> throw CustomExceptionFactory.conflict(ErrorCode.AUTH_004)
                    is DuplicateKeyException -> throw CustomExceptionFactory.serviceUnavailable(ErrorCode.INTERNAL_001)
                    else -> throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_001)
                }
            }
            .then()
    }

    override fun findByUserId(): Flux<EmotionInternalDTO> {
        return currentUser.get()
            .flatMapMany { user ->
                emotionRepository.findByUserId(
                    user.userId
                        ?: return@flatMapMany Flux.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_003))
                )}
    }
}