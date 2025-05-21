package com.dsm.hhh.internal.data.repository.diary.mapper

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Note
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Title
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.diary.EmotionDiaryEntity

class EmotionDiaryEntityMapper private constructor() {

    companion object {

        fun toEntity(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): EmotionDiaryEntity {
            return EmotionDiaryEntity(
                title = emotionDiaryInternalDTO.title.value(),
                note = emotionDiaryInternalDTO.note.value(),
                userId = emotionDiaryInternalDTO.userId?.value()
                    ?: throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_002)
            )
        }

        fun toInternalDTO(emotionDiaryEntity: EmotionDiaryEntity): EmotionDiaryInternalDTO {
            return EmotionDiaryInternalDTO(
                diaryId = DiaryId(emotionDiaryEntity.id
                    ?: throw CustomExceptionFactory.internalServerError(ErrorCode.INTERNAL_002)
                ),
                title = Title(emotionDiaryEntity.title),
                note = Note(emotionDiaryEntity.note),
                createdAt = CreatedAt(emotionDiaryEntity.createdAt),
                userId = UserId(emotionDiaryEntity.userId)
            )
        }

    }

}