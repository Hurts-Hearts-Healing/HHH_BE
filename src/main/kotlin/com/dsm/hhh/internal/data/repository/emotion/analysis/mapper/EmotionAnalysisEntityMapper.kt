package com.dsm.hhh.internal.data.repository.emotion.analysis.mapper

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisEntity

class EmotionAnalysisEntityMapper private constructor(){
    companion object{

        fun toEntity(dto: EmotionAnalysisInternalDTO): EmotionAnalysisEntity {
            return EmotionAnalysisEntity(
                userId = dto.userId,
                diaryId = dto.diaryId ?: throw IllegalArgumentException("diary Id가 null입니다."),
                emotion = dto.emotion
            )
        }

        fun toDto(entity: EmotionAnalysisEntity): EmotionAnalysisInternalDTO {
            return EmotionAnalysisInternalDTO(
                userId = entity.userId,
                diaryId = entity.diaryId,
                emotion = entity.emotion
            )
        }
    }
}