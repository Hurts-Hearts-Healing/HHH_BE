package com.dsm.hhh.internal.data.repository.emotion

import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.mapper.EmotionMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
class EmotionRepositoryImpl(
    private val emotionMongoRepository: EmotionMongoRepository
): EmotionRepository {
    override fun save(emotionInternalDTO: EmotionInternalDTO): Mono<Void> {
        val entity = EmotionMapper.toEntity(emotionInternalDTO)

        return emotionMongoRepository.save(entity).then()
    }

    override fun findByUserId(userId: UserId): Flux<EmotionInternalDTO> {
        return emotionMongoRepository.findByUserId(userId.value())
            .map(EmotionMapper::toDTO)
    }
}