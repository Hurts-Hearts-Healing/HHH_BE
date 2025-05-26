package com.dsm.hhh.internal.data.repository.emotion.emoji.mapper

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.emoji.EmotionEmojiEntity

class EmotionEmojiMapper private constructor() {

    companion object {

        fun toEntity(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): EmotionEmojiEntity {
            return EmotionEmojiEntity(
                userId = emotionEmojiInternalDTO.userId?.value()
                    ?: throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_002),
                emotion = emotionEmojiInternalDTO.emotion.value()
            )
        }

        fun toDTO(emotionEmojiEntity: EmotionEmojiEntity): EmotionEmojiInternalDTO {
            return EmotionEmojiInternalDTO(
                userId = UserId(emotionEmojiEntity.userId),
                emotion = Emotion(emotionEmojiEntity.emotion),
                createdAt = CreatedAt(emotionEmojiEntity.createdAt)
            )

        }

    }

}