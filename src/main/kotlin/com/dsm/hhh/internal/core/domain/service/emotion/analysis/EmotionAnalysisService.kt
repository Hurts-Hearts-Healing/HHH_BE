package com.dsm.hhh.internal.core.domain.service.emotion.analysis

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.client.OpenAiClient
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.usecase.emotion.analysis.EmotionAnalysisUseCase
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisRepository
import com.dsm.hhh.internal.data.repository.emotion.diary.EmotionDiaryRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmotionAnalysisService(
    private val emotionAnalysisRepository: EmotionAnalysisRepository,
    private val emotionDiaryRepository: EmotionDiaryRepository,
    private val currentUser: CurrentUser,
    private val openAiClient: OpenAiClient
) : EmotionAnalysisUseCase {
    override fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void> {
        return currentUser.getCurrentUser()
            .switchIfEmpty(Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005)))
            .flatMap { user ->
                val userId = user.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionDiaryRepository.findByUserIdAndCreatedAt(userId, CreatedAt.now())
                    .switchIfEmpty(Mono.error(CustomExceptionFactory.notFound(ErrorCode.DIARY_001)))
                    .flatMap { user ->
                        user.userId?.let { userId ->
                            emotionDiaryRepository.findByUserIdAndCreatedAt(userId, CreatedAt.now())
                                .switchIfEmpty(Mono.error(CustomExceptionFactory.notFound(ErrorCode.DIARY_001)))
                                .flatMap { diary ->
                                    Mono.defer {
                                        val content = diary.note.value()
                                        val emotion = openAiClient.analyzeEmotion(content)

                                        val analysis = EmotionAnalysisInternalDTO(
                                            emotion = emotion,
                                        )

                                        emotionAnalysisRepository.save(analysis)

                                    }
                                }
                        } ?: Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))
                    }
            }
    }
}