package com.dsm.hhh.internal.core.usecase.emotion.graph

import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphResponse
import reactor.core.publisher.Mono

interface EmotionGraphUseCase {

    fun getDailyEmotionGraph(): Mono<EmotionGraphResponse>

    fun getWeeklyEmotionGraph(): Mono<EmotionGraphResponse>

    fun getMonthlyEmotionGraph(): Mono<EmotionGraphResponse>

}