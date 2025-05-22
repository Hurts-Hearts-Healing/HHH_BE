package com.dsm.hhh.internal.core.domain.service.emotion

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.usecase.emotion.EmotionUseCase
import com.dsm.hhh.internal.data.repository.emotion.EmotionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
private class EmotionService(
    private val emotionRepository: EmotionRepository,
    private val currentUser: CurrentUser
) : EmotionUseCase {

    override fun register(emotionInternalDTO: EmotionInternalDTO): Mono<Void> {
        return currentUser.get()
            .doOnNext { user -> emotionInternalDTO.userId = user.userId }
            .flatMap {
                val userId = emotionInternalDTO.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionRepository.findByUserIdAndCreatedAt(userId, CreatedAt.now())
                    .hasElement()
            }
            .flatMap { exists ->
                if (exists) {
                    Mono.error(CustomExceptionFactory.conflict(ErrorCode.EMOTION_001))
                } else {
                    emotionRepository.save(emotionInternalDTO)
                }
            }
    }

    override fun findByUserId(): Flux<EmotionInternalDTO> {
        return currentUser.get()
            .flatMapMany { user ->
                val userId = user.userId
                    ?: return@flatMapMany Flux.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_003))

                emotionRepository.findByUserId(userId)
            }
    }

}