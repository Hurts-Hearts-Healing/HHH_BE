package com.dsm.hhh.external.web.rest.emotion.emoji.mapper

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.external.web.rest.emotion.emoji.form.EmotionEmojiRequestForm
import com.dsm.hhh.external.web.rest.emotion.emoji.response.EmotionResponse
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.model.dto.emotion.emoji.EmotionEmojiInternalDTO

class EmotionEmojiMapper private constructor() {

    companion object{

        fun emotionRequestFormToInternalDTO(emotionEmojiRequestForm: EmotionEmojiRequestForm): EmotionEmojiInternalDTO{
            return EmotionEmojiInternalDTO(
                emotion = emotionEmojiRequestForm.emotion
            )
        }

        fun emotionDTOToEmotionResponse(emotionEmojiInternalDTO: EmotionEmojiInternalDTO): EmotionResponse {
            return EmotionResponse(
                emotion = emotionEmojiInternalDTO.emotion,
                createdAt = emotionEmojiInternalDTO.createdAt
                    ?: throw CustomExceptionFactory.badRequest(ErrorCode.INTERNAL_001)
            )
        }

    }

}