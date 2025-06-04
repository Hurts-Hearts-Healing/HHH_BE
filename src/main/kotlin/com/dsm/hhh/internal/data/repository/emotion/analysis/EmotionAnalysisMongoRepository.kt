package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface EmotionAnalysisMongoRepository : ReactiveMongoRepository<EmotionAnalysisEntity, String> {

    fun findByUserIdAndDiaryId(userId: String, diaryId: String): Mono<EmotionAnalysisEntity>

}