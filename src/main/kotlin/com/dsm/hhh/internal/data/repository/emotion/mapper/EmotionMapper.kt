package com.dsm.hhh.internal.data.repository.emotion.mapper

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.EmotionEntity

class EmotionMapper private constructor() {

    companion object {

        fun toEntity(emotionInternalDTO: EmotionInternalDTO): EmotionEntity {
            return EmotionEntity(
                userId = emotionInternalDTO.userId?.value()
                    ?: throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_002),
                emotion = emotionInternalDTO.emotion.value()
            )
        }

        fun toDTO(emotionEntity: EmotionEntity): EmotionInternalDTO {
            return EmotionInternalDTO(
                userId = UserId(emotionEntity.userId),
                emotion = Emotion(emotionEntity.emotion),
                createdAt = CreatedAt(emotionEntity.createdAt)
            )

        }

    }

}