package com.dsm.hhh.internal.data.repository.emotion.emoji

import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.emoji.mapper.EmotionEmojiMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
private class EmotionEmojiRepositoryImpl(
    private val emotionEmojiMongoRepository: EmotionEmojiMongoRepository
): EmotionEmojiRepository {

    override fun save(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): Mono<Void> {
        val emojiEntity = EmotionEmojiMapper.toEntity(emotionEmojiInternalDTO)

        return emotionEmojiMongoRepository.save(emojiEntity)
            .then()
    }

    override fun findByUserId(userId: UserId): Flux<EmotionEmojiInternalDTO> {
        return emotionEmojiMongoRepository.findByUserId(userId.value())
            .map(EmotionEmojiMapper::toDTO)
    }

    override fun findByUserIdAndCreatedAt(
        userId: UserId,
        createdAt: CreatedAt
    ): Mono<EmotionEmojiInternalDTO> {
        return emotionEmojiMongoRepository.findByUserIdAndCreatedAt(userId.value(), createdAt.value())
            .map(EmotionEmojiMapper::toDTO)
    }

}