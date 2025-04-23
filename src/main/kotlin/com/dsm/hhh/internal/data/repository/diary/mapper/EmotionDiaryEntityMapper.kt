package com.dsm.hhh.internal.data.repository.diary.mapper

import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO
import com.dsm.hhh.internal.data.repository.diary.EmotionDiaryEntity

class EmotionDiaryEntityMapper private constructor() {

    companion object {

        fun toEntity(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): EmotionDiaryEntity {
            return EmotionDiaryEntity(
                title = emotionDiaryInternalDTO.title.value(),
                note = emotionDiaryInternalDTO.note.value()
            )
        }

    }

}