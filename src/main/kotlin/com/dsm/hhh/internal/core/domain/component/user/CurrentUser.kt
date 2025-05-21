package com.dsm.hhh.internal.core.domain.component.user

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.dsm.hhh.internal.core.domain.model.dto.user.UserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.user.UserRepository
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.Principal

@Component
class CurrentUser(
    private val userRepository: UserRepository
) {

    fun get(): Mono<UserInternalDTO> {
        return ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .map(Principal::getName)
            .flatMap{ userId -> userRepository.findById(UserId(userId)) }
            .switchIfEmpty(Mono.error(CustomExceptionFactory.unauthorized(ErrorCode.AUTH_005)))
    }

}