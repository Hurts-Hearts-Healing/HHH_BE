package com.dsm.hhh.external.web.rest.emotion.graph

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.graph.form.EmotionGraphRequestParam
import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphResponse
import com.dsm.hhh.internal.core.usecase.emotion.graph.EmotionGraphUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class EmotionGraphRestController(
    private val emotionGraphUseCase: EmotionGraphUseCase
) {

    @GetMapping(RestApiSpec.EMOTION_GRAPH)
    @ResponseStatus(HttpStatus.OK)
    fun getEmotionGraph(@RequestParam(name = "period") emotionGraphRequestParam: EmotionGraphRequestParam): Mono<EmotionGraphResponse> {
        return when (emotionGraphRequestParam) {
            EmotionGraphRequestParam.DAY -> emotionGraphUseCase.getDailyEmotionGraph()
            EmotionGraphRequestParam.MONTH -> emotionGraphUseCase.getMonthlyEmotionGraph()
            EmotionGraphRequestParam.WEEK -> emotionGraphUseCase.getWeeklyEmotionGraph()
        }
    }

}