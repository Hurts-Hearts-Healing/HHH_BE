package com.dsm.hhh.internal.data.repository.emotion.diary

import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.diary.mapper.EmotionDiaryEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class EmotionDiaryRepositoryImpl(
    private val emotionDiaryMongoRepository: EmotionDiaryMongoRepository
): EmotionDiaryRepository {

    override fun save(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): Mono<Void> {
        val entity = EmotionDiaryEntityMapper.toEntity(emotionDiaryInternalDTO)

        return emotionDiaryMongoRepository.save(entity)
            .then();
    }

    override fun findByUserId(userId: UserId): Flux<EmotionDiaryInternalDTO> {
        return emotionDiaryMongoRepository.findByUserId(userId.value())
            .map(EmotionDiaryEntityMapper::toInternalDTO)
    }

    override fun findByUserIdAndCreatedAt(
        userId: UserId,
        createdAt: CreatedAt
    ): Mono<EmotionDiaryInternalDTO> {
        return emotionDiaryMongoRepository.findByUserIdAndCreatedAt(userId.value(), createdAt.value())
            .map(EmotionDiaryEntityMapper::toInternalDTO)
    }

    override fun findById(dirayId: DiaryId): Mono<EmotionDiaryInternalDTO> {
        return emotionDiaryMongoRepository.findById(dirayId.value())
            .map(EmotionDiaryEntityMapper::toInternalDTO)
    }

}