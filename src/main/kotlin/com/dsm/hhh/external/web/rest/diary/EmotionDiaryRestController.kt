package com.dsm.hhh.external.web.rest.diary

import com.dsm.hhh.external.web.rest.diary.form.EmotionDiaryWriteRequestForm
import com.dsm.hhh.external.web.rest.diary.mapper.EmotionDiaryMapper
import com.dsm.hhh.internal.core.usecase.diary.EmotionDiaryUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/emotion/diary")
class EmotionDiaryRestController(
    private val emotionDiaryUseCase: EmotionDiaryUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun write(emotionDiaryWriteRequestForm: EmotionDiaryWriteRequestForm): Mono<Void> {
        val dto = EmotionDiaryMapper.emotionDiaryWriteRequestFormToInternalDTO(emotionDiaryWriteRequestForm)

        return emotionDiaryUseCase.write(dto)
    }

}