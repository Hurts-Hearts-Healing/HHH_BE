package com.dsm.hhh.internal.core.domain.service.emotion.emoji

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.usecase.emotion.emoji.EmotionEmojiUseCase
import com.dsm.hhh.internal.data.repository.emotion.emoji.EmotionEmojiRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
private class EmotionEmojiService(
    private val emotionEmojiRepository: EmotionEmojiRepository,
    private val currentUser: CurrentUser
) : EmotionEmojiUseCase {

    override fun register(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): Mono<Void> {
        return currentUser.getCurrentUser()
            .doOnNext { user -> emotionEmojiInternalDTO.userId = user.userId }
            .flatMap {
                val userId = emotionEmojiInternalDTO.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionEmojiRepository.findByUserIdAndCreatedAt(userId, CreatedAt.now())
                    .hasElement()
            }
            .flatMap { exists ->
                if (exists) {
                    Mono.error(CustomExceptionFactory.conflict(ErrorCode.EMOTION_001))
                } else {
                    emotionEmojiRepository.save(emotionEmojiInternalDTO)
                }
            }
    }

    override fun findByUserId(): Flux<EmotionEmojiInternalDTO> {
        return currentUser.getCurrentUser()
            .flatMapMany { user ->
                val userId = user.userId
                    ?: return@flatMapMany Flux.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_003))

                emotionEmojiRepository.findByUserId(userId)
            }
    }

}