package com.dsm.hhh.internal.data.repository.user

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import reactor.core.publisher.Mono

/**
 * UserRepository - 사용자 도메인 리포지토리 인터페이스
 * <p>
 * 도메인 계층에서 사용할 사용자 관련 데이터 접근 기능을 정의합니다.
 * 구현체에서 실제 데이터 저장소 연동을 처리합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
interface UserRepository {

    /**
     * 사용자 정보를 저장합니다.
     * @param userInternalDTO 저장할 사용자 DTO
     * @return 저장된 사용자 정보를 담은 Mono
     */
    fun save(userInternalDTO: UserInternalDTO): Mono<UserInternalDTO>
    
    /**
     * 이메일로 사용자를 조회합니다.
     * @param email 조회할 사용자의 이메일
     * @return 조회된 사용자 정보를 담은 Mono 또는 빈 Mono
     */
    fun findByEmail(email: Email): Mono<UserInternalDTO>

    fun findById(userId: UserId): Mono<UserInternalDTO>

}