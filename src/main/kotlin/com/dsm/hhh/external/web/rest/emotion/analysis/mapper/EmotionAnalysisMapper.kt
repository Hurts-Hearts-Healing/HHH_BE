package com.dsm.hhh.external.web.rest.emotion.analysis.mapper

import com.dsm.hhh.external.web.rest.emotion.analysis.form.EmotionAnalysisRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.analysis.EmotionAnalysisEntity

class EmotionAnalysisMapper private constructor() {

    companion object {

//        fun toResponse(dto: EmotionAnalysisInternalDTO): EmotionAnalysisResponse {
//            return EmotionAnalysisResponse(
//                userId = dto.userId,
//                emotion = dto.emotion,
//                score = dto.score,
//                createdAt = dto.createdAt
//            )
//        }

        fun toEntity(dto: EmotionAnalysisInternalDTO): EmotionAnalysisEntity {
            return EmotionAnalysisEntity(
                userId = dto.userId ?: throw IllegalArgumentException("userId must not be null"),
                emotion = dto.emotion,
                score = dto.score
            )
        }

        fun toDTO(entity: EmotionAnalysisRequestForm): EmotionDiaryInternalDTO {
            return EmotionDiaryInternalDTO(
                title = entity.title,
                note = entity.note
            )
        }
    }
}