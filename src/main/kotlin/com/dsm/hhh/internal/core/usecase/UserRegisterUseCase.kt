package com.dsm.hhh.internal.core.usecase

import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import reactor.core.publisher.Mono

/**
 * UserRegisterUseCase - 사용자 등록 유스케이스 인터페이스
 * <p>
 * 사용자 등록 비즈니스 로직을 정의한 함수형 인터페이스입니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
fun interface UserRegisterUseCase {

    /**
     * 사용자 정보를 등록합니다.
     * @param userInternalDTO 등록할 사용자 정보 DTO
     * @return 처리 결과를 나타내는 Mono<Void>
     */
    fun register(userInternalDTO: UserInternalDTO): Mono<Void>

}