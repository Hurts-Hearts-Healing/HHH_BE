package com.dsm.hhh.internal.data.repository.emotion.emoji

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

interface EmotionEmojiMongoRepository : ReactiveMongoRepository<EmotionEmojiEntity, String> {

    fun findByUserId(userId: String): Flux<EmotionEmojiEntity>

    fun findByUserIdAndCreatedAt(userId: String, createdAt: LocalDate): Mono<EmotionEmojiEntity>

}