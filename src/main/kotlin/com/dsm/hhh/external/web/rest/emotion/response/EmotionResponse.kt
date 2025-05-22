package com.dsm.hhh.external.web.rest.emotion.response


data class EmotionResponse(
    val emotion: String,
    val userId: String,
)

class EmotionListResponse(
    val emotionList: List<EmotionResponse>
)