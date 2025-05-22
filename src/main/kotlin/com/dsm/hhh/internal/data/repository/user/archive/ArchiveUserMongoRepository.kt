package com.dsm.hhh.internal.data.repository.user.archive

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ArchiveUserMongoRepository: ReactiveMongoRepository<ArchiveUserEntity, String> {
    fun findByEmail(email: String): Mono<ArchiveUserEntity>
}