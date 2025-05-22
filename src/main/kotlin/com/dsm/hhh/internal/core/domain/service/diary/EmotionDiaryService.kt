package com.dsm.hhh.internal.core.domain.service.diary

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.usecase.diary.EmotionDiaryUseCase
import com.dsm.hhh.internal.data.repository.diary.EmotionDiaryRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
private class EmotionDiaryService(
    private val emotionDiaryRepository: EmotionDiaryRepository,
    private val currentUser: CurrentUser
) : EmotionDiaryUseCase {

    override fun write(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void> {
        return currentUser.get()
            .doOnNext { user -> emotionDiaryInternalDTO.userId = user.userId }
            .flatMap {
                val userId = emotionDiaryInternalDTO.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionDiaryRepository.findByUserIdAndCreatedAt(userId, CreatedAt.now()
                ).hasElement()
            }
            .flatMap { exists ->
                if (exists) {
                    Mono.error(CustomExceptionFactory.conflict(ErrorCode.DIARY_001))
                } else {
                    emotionDiaryRepository.save(emotionDiaryInternalDTO)
                }
            }
    }

    override fun getMyDiaries(): Flux<EmotionDiaryInternalDTO> {
        return currentUser.get()
            .flatMapMany { user ->
                val userId = user.userId
                    ?: return@flatMapMany Flux.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionDiaryRepository.findByUserId(userId)
            }
    }

}