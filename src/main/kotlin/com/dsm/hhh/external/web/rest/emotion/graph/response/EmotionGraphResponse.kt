package com.dsm.hhh.external.web.rest.emotion.graph.response

class EmotionGraphResponse(
    val graphData: List<EmotionGraphDetailResponse>
)

class EmotionGraphDetailResponse(
    val period: String,

    /**
     * happiness >= 8 -> 매우 행복(status)
     * happiness >= 6 -> 행복(status)
     * happiness >= 4 -> 보통(status)
     * happiness >= 2 -> 슬픔(status)
     * happiness >= 0 -> 매우 슬픔(status)
     */
    val happiness: Double,
    val status: String
)