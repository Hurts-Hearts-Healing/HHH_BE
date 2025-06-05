package com.dsm.hhh.internal.data.repository.emotion.analysis

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface EmotionAnalysisMongoRepository : ReactiveMongoRepository<EmotionAnalysisEntity, String> {
    fun findByUserId(userId: String): Flux<EmotionAnalysisEntity>
}