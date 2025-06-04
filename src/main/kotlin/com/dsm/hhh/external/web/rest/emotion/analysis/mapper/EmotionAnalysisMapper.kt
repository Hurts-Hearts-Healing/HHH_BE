package com.dsm.hhh.external.web.rest.emotion.analysis.mapper

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisEntity

class EmotionAnalysisMapper private constructor() {

    companion object {

        fun toResponse(dto: EmotionAnalysisInternalDTO): EmotionAnalysisResponse {
            return EmotionAnalysisResponse(
                userId = dto.userId,
                diaryId = dto.diaryId,
                emotion = dto.emotion,
                createdAt = dto.createdAt
            )
        }

        fun toEntity(dto: EmotionAnalysisInternalDTO): EmotionAnalysisEntity {
            return EmotionAnalysisEntity(
                userId = dto.userId,
                diaryId = dto.diaryId ?: throw IllegalArgumentException(""),
                emotion = dto.emotion,
                createdAt = dto.createdAt
            )
        }
    }
}