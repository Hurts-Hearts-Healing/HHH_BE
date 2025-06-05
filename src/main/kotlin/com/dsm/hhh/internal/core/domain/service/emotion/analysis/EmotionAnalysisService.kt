package com.dsm.hhh.internal.core.domain.service.emotion.analysis

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.client.GeminiClient
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.usecase.emotion.analysis.EmotionAnalysisUseCase
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisRepository
import com.dsm.hhh.internal.data.repository.emotion.diary.EmotionDiaryRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmotionAnalysisService(
    private val emotionAnalysisRepository: EmotionAnalysisRepository,
    private val emotionDiaryRepository: EmotionDiaryRepository,
    private val currentUser: CurrentUser,
    private val openAiClient: GeminiClient
) : EmotionAnalysisUseCase {

    override fun save(diaryId: String): Mono<Void> {
        return currentUser.getCurrentUser()
            .switchIfEmpty(Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005)))
            .flatMap { user ->
                val userId = user.userId
                    ?: return@flatMap Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionDiaryRepository.findById(DiaryId(diaryId))
                    .switchIfEmpty(Mono.error(CustomExceptionFactory.notFound(ErrorCode.DIARY_001)))
                    .flatMap { diary ->
                        val content = diary.note.value()

                        openAiClient.analyzeEmotion(content)
                            .flatMap { emotionResult ->
                                val analysis = EmotionAnalysisInternalDTO(
                                    userId = user.userId.value(),
                                    diaryId = diaryId,
                                    emotion = emotionResult
                                )
                                emotionAnalysisRepository.save(analysis)
                            }
                    }
            }
            .then()
    }

    override fun findByUserId(): Flux<EmotionAnalysisInternalDTO> {
        return currentUser.getCurrentUser()
            .switchIfEmpty(Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005)))
            .flatMapMany { user ->
                val userId = user.userId
                    ?: return@flatMapMany Flux.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005))

                emotionAnalysisRepository.findByUserId(userId)
            }
    }
}
