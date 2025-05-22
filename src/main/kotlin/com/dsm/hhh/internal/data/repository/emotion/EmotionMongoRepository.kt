package com.dsm.hhh.internal.data.repository.emotion

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

interface EmotionMongoRepository : ReactiveMongoRepository<EmotionEntity, String> {

    fun findByUserId(userId: String): Flux<EmotionEntity>

    fun findByUserIdAndCreatedAt(userId: String, createdAt: LocalDate): Mono<EmotionEntity>

}