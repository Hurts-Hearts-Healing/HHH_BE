package com.dsm.hhh.external.web.rest.emotion.mapper

import com.dsm.hhh.external.web.rest.emotion.form.EmotionRequestForm
import com.dsm.hhh.external.web.rest.emotion.response.EmotionResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO

class EmotionMapper private constructor(){
    companion object{
        fun emotionRequestFormToInternalDTO(emotionRequestForm: EmotionRequestForm): EmotionInternalDTO{
            return EmotionInternalDTO(
                userId = emotionRequestForm.userId,
                emotion = emotionRequestForm.emotion
            )
        }

        fun emotionDTOToEmotionResponse(emotionInternalDTO: EmotionInternalDTO): EmotionResponse{
            return EmotionResponse(
                emotion = emotionInternalDTO.emotion,
                userId = emotionInternalDTO.userId
            )
        }
    }
}