package com.dsm.hhh.internal.core.domain.service.diary

import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.usecase.diary.EmotionDiaryUseCase
import com.dsm.hhh.internal.data.repository.diary.EmotionDiaryRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmotionDiaryService(
    private val emotionDiaryRepository: EmotionDiaryRepository
): EmotionDiaryUseCase {

    override fun write(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void> {
        return emotionDiaryRepository.save(emotionDiaryInternalDTO)
            .then()
    }

}