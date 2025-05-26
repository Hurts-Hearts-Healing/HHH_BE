package com.dsm.hhh.external.web.rest.emotion.diary.mapper

import com.dsm.hhh.external.web.rest.emotion.diary.form.EmotionDiaryWriteRequestForm
import com.dsm.hhh.external.web.rest.emotion.diary.response.EmotionDiaryResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.diary.EmotionDiaryInternalDTO

class EmotionDiaryMapper private constructor() {

    companion object {

        fun emotionDiaryWriteRequestFormToInternalDTO(emotionDiaryWriteRequestForm: EmotionDiaryWriteRequestForm): EmotionDiaryInternalDTO {
            return EmotionDiaryInternalDTO(
                title = emotionDiaryWriteRequestForm.title,
                note = emotionDiaryWriteRequestForm.note
            )
        }

        fun emotionDiaryDTOToEmotionDiaryResponse(emotionDiaryInternalDTO: EmotionDiaryInternalDTO): EmotionDiaryResponse {
            return EmotionDiaryResponse(
                diaryId = emotionDiaryInternalDTO.diaryId,
                title = emotionDiaryInternalDTO.title,
                note = emotionDiaryInternalDTO.note,
                createdAt = emotionDiaryInternalDTO.createdAt
            )
        }

    }

}