package com.dsm.hhh.internal.data.repository.diary

import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.data.repository.diary.mapper.EmotionDiaryEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class EmotionDiaryRepositoryImpl(
    val emotionDiaryMongoRepository: EmotionDiaryMongoRepository
): EmotionDiaryRepository {

    override fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void> {
        val entity = EmotionDiaryEntityMapper.toEntity(emotionDiaryInternalDTO)

        return emotionDiaryMongoRepository.save(entity)
            .then();
    }

}