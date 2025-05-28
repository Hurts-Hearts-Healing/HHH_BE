package com.dsm.hhh.external.web.rest.emotion.analysis

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.analysis.form.EmotionAnalysisRequestForm
import com.dsm.hhh.external.web.rest.emotion.analysis.mapper.EmotionAnalysisMapper
import com.dsm.hhh.internal.core.usecase.emotion.analysis.EmotionAnalysisUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class EmotionAnalysisRestController(
    private val emotionAnalysisUseCase: EmotionAnalysisUseCase
) {
    @PostMapping(RestApiSpec.EMOTION_ANALYSIS)
    @ResponseStatus(HttpStatus.OK)
    fun save(@RequestBody emotionAnalysisRequestForm: EmotionAnalysisRequestForm): Mono<Void>{
        val dto = EmotionAnalysisMapper.toDTO(emotionAnalysisRequestForm)
        return emotionAnalysisUseCase.save(dto)
    }
}