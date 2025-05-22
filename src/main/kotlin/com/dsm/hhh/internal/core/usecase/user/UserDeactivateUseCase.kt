package com.dsm.hhh.internal.core.usecase.user

import reactor.core.publisher.Mono

interface UserDeactivateUseCase {

    fun deactivateUser(): Mono<Void>

}