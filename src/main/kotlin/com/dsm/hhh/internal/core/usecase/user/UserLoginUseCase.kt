package com.dsm.hhh.internal.core.usecase.user

import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.Password
import reactor.core.publisher.Mono

interface UserLoginUseCase {

    fun login(email: Email, password: Password): Mono<String>

}