package com.dsm.hhh.internal.data.repository.emotion

import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface EmotionMongoRepository : ReactiveMongoRepository<EmotionEntity, String>{
    fun findByUserId(userId: String): Flux<EmotionEntity>
}