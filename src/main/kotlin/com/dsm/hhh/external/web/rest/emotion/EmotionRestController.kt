package com.dsm.hhh.external.web.rest.emotion

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.form.EmotionRequestForm
import com.dsm.hhh.external.web.rest.emotion.mapper.EmotionMapper
import com.dsm.hhh.external.web.rest.emotion.response.EmotionListResponse
import com.dsm.hhh.external.web.rest.emotion.response.EmotionResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.EmotionInternalDTO
import com.dsm.hhh.internal.core.usecase.emotion.EmotionUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class EmotionRestController(
    private val emotionUseCase: EmotionUseCase
) {
    @PostMapping(RestApiSpec.EMOTION_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody emotionRequestForm: EmotionRequestForm): Mono<Void> {
        val dto = EmotionMapper.emotionRequestFormToInternalDTO(emotionRequestForm)

        return emotionUseCase.register(dto)
    }

    @GetMapping(RestApiSpec.EMOTION_READ)
    @ResponseStatus(HttpStatus.OK)
    fun getMyEmotion(): Mono<EmotionListResponse>{
        return emotionUseCase.findByUserId()
            .map(EmotionMapper::emotionDTOToEmotionResponse)
            .collectList()
            .map { emotionList -> EmotionListResponse(emotionList) }
    }
}