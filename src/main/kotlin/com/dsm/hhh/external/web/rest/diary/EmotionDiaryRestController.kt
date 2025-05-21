package com.dsm.hhh.external.web.rest.diary

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.diary.form.EmotionDiaryWriteRequestForm
import com.dsm.hhh.external.web.rest.diary.mapper.EmotionDiaryMapper
import com.dsm.hhh.external.web.rest.diary.response.EmotionDiaryResponse
import com.dsm.hhh.internal.core.usecase.diary.EmotionDiaryUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
private class EmotionDiaryRestController(
    private val emotionDiaryUseCase: EmotionDiaryUseCase
) {

    @PostMapping(RestApiSpec.DIARY_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun write(@RequestBody emotionDiaryWriteRequestForm: EmotionDiaryWriteRequestForm): Mono<Void> {
        val dto = EmotionDiaryMapper.emotionDiaryWriteRequestFormToInternalDTO(emotionDiaryWriteRequestForm)

        return emotionDiaryUseCase.write(dto)
    }

    @GetMapping(RestApiSpec.DIARY_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun getMyEmotionDiaries(): Flux<EmotionDiaryResponse> {
        return emotionDiaryUseCase.getMyDiaries()
            .map(EmotionDiaryMapper::emotionDiaryDTOToEmotionDiaryResponse)
    }

}