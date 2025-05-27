package com.dsm.hhh.internal.data.repository.emotion.analysis.mapper

import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisEntity

class EmotionAnalysisEntityMapper private constructor(){
    companion object{

        fun toEntity(dto: EmotionAnalysisInternalDTO): EmotionAnalysisEntity {
            return EmotionAnalysisEntity(
                userId = dto.userId ?: throw IllegalArgumentException("userId는 null일 수 없습니다."),
                emotion = dto.emotion,
                score = dto.score
            )
        }

        fun toDto(entity: EmotionAnalysisEntity): EmotionAnalysisInternalDTO {
            return EmotionAnalysisInternalDTO(
                userId = entity.userId,
                emotion = entity.emotion,
                createdAt = entity.createdAt,
                score = entity.score
            )
        }
    }
}