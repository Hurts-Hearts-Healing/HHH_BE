package com.dsm.hhh.internal.data.repository.emotion.analysis

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EmotionAnalysisMongoRepository : ReactiveMongoRepository<EmotionAnalysisEntity, String> {
}