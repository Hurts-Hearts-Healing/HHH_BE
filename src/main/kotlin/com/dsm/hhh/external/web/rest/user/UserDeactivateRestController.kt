package com.dsm.hhh.external.web.rest.user

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.internal.core.usecase.user.UserDeactivateUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class UserDeactivateRestController(
    private val userDeactivateUseCase: UserDeactivateUseCase
) {

    @DeleteMapping(RestApiSpec.USER_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deactivateUser(): Mono<Void> = userDeactivateUseCase.deactivateUser()

}