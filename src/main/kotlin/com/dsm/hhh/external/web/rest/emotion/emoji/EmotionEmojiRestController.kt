package com.dsm.hhh.external.web.rest.emotion.emoji

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.emoji.form.EmotionEmojiRequestForm
import com.dsm.hhh.external.web.rest.emotion.emoji.mapper.EmotionEmojiMapper
import com.dsm.hhh.external.web.rest.emotion.emoji.response.EmotionEmojiListResponse
import com.dsm.hhh.internal.core.usecase.emotion.emoji.EmotionEmojiUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class EmotionRestController(
    private val emotionEmojiUseCase: EmotionEmojiUseCase
) {

    @PostMapping(RestApiSpec.EMOTION_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody emotionEmojiRequestForm: EmotionEmojiRequestForm): Mono<Void> {
        val dto = EmotionEmojiMapper.emotionRequestFormToInternalDTO(emotionEmojiRequestForm)

        return emotionEmojiUseCase.register(dto)
    }

    @GetMapping(RestApiSpec.EMOTION_READ)
    @ResponseStatus(HttpStatus.OK)
    fun getMyEmotion(): Mono<EmotionEmojiListResponse>{
        return emotionEmojiUseCase.findByUserId()
            .map(EmotionEmojiMapper::emotionDTOToEmotionResponse)
            .collectList()
            .map { emotionList -> EmotionEmojiListResponse(emotionList) }
    }

}