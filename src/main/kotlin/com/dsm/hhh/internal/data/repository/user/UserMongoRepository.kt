package com.dsm.hhh.internal.data.repository.user

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

/**
 * UserMongoRepository - Spring Data MongoDB Reactive Repository
 * <p>
 * MongoDB와의 반응형(Reactive) 연동을 제공하는 Spring Data 인터페이스
 * 기본 CRUD 연산을 지원하며 UserEntity를 관리합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
interface UserMongoRepository: ReactiveMongoRepository<UserEntity, String> {

    /**
     * 이메일로 사용자를 조회합니다.
     * @param email 조회할 사용자의 이메일
     * @return 조회된 사용자 정보를 담은 Mono 또는 빈 Mono
     */
    fun findByEmail(email: String): Mono<UserEntity>

}