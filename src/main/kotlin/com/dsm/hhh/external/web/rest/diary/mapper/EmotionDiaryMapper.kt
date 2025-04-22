package com.dsm.hhh.external.web.rest.diary.mapper

import com.dsm.hhh.external.web.rest.diary.form.EmotionDiaryWriteRequestForm
import com.dsm.hhh.internal.core.domain.model.dto.diary.EmotionDiaryInternalDTO

class EmotionDiaryMapper private constructor() {

    companion object {

        fun emotionDiaryWriteRequestFormToInternalDTO(emotionDiaryWriteRequestForm: EmotionDiaryWriteRequestForm): EmotionDiaryInternalDTO {
            return EmotionDiaryInternalDTO(
                title = emotionDiaryWriteRequestForm.title,
                note = emotionDiaryWriteRequestForm.note
            )
        }

    }

}