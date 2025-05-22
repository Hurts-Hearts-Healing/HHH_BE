package com.dsm.hhh.internal.data.repository.emotion.mapper

import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.EmotionEntity

class EmotionMapper private constructor() {
    companion object {
        fun toEntity(emotionInternalDTO: EmotionInternalDTO): EmotionEntity {
            return EmotionEntity(
                userId = emotionInternalDTO.userId,
                emotion = emotionInternalDTO.emotion
            )
        }

        fun toDTO(emotionEntity: EmotionEntity): EmotionInternalDTO {
            return EmotionInternalDTO(
                userId = emotionEntity.userId,
                emotion = emotionEntity.emotion,
            )

        }
    }
}