package com.dsm.hhh.external.web.rest.emotion.diary

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.emotion.diary.form.EmotionDiaryWriteRequestForm
import com.dsm.hhh.external.web.rest.emotion.diary.mapper.EmotionDiaryMapper
import com.dsm.hhh.external.web.rest.emotion.diary.response.EmotionDiariesResponse
import com.dsm.hhh.internal.core.usecase.emotion.diary.EmotionDiaryUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@RestController
private class EmotionDiaryRestController(
    private val emotionDiaryUseCase: EmotionDiaryUseCase
) {

    @PostMapping(RestApiSpec.DIARY_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun writeDiary(@RequestBody emotionDiaryWriteRequestForm: EmotionDiaryWriteRequestForm): Mono<Void> {
        val dto = EmotionDiaryMapper.emotionDiaryWriteRequestFormToInternalDTO(emotionDiaryWriteRequestForm)

        return emotionDiaryUseCase.writeDiary(dto)
    }

    @GetMapping(RestApiSpec.DIARY_ALL)
    @ResponseStatus(HttpStatus.OK)
    fun getUserEmotionDiaries(): Mono<EmotionDiariesResponse> {
        return emotionDiaryUseCase.getUserDiaries()
            .map(EmotionDiaryMapper::emotionDiaryDTOToEmotionDiaryResponse)
            .collectList()
            .map { diaries -> EmotionDiariesResponse(diaries) }
    }

}