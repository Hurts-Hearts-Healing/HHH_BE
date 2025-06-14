package com.dsm.hhh.external.web.rest.emotion.analysis

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.analysis.mapper.EmotionAnalysisMapper
import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisListResponse
import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.usecase.emotion.analysis.EmotionAnalysisUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class EmotionAnalysisRestController(
    private val emotionAnalysisUseCase: EmotionAnalysisUseCase
) {
    @PostMapping(RestApiSpec.EMOTION_ANALYSIS)
    @ResponseStatus(HttpStatus.OK)
    fun save(@PathVariable("diary-id") diaryId: String): Mono<Void> {
        return emotionAnalysisUseCase.save(diaryId)
    }

    @GetMapping(RestApiSpec.EMOTION_GET)
    @ResponseStatus(HttpStatus.OK)
    fun findByUserId(): Mono<EmotionAnalysisListResponse> {
        return emotionAnalysisUseCase.findByUserId()
            .map(EmotionAnalysisMapper::toResponse)
            .collectList()
            .map { analysis -> EmotionAnalysisListResponse(analysis) }
    }
}