package com.dsm.hhh.external.web.rest.user

import com.dsm.hhh.external.web.rest.RestApiSpec
import com.dsm.hhh.external.web.rest.user.response.UserInfoResponse
import com.dsm.hhh.internal.core.domain.component.user.CurrentUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
private class UserRestController(
    val currentUser: CurrentUser
) {

    @GetMapping(RestApiSpec.USER_GET)
    @ResponseStatus(HttpStatus.OK)
    fun getMyInfo(): Mono<UserInfoResponse> {
        return currentUser.get()
            .map { user -> UserInfoResponse(
                name = user.name,
                email = user.email,
                birthday = user.birthday,
                breakupDate = user.breakupDate,
                emotionStatus = user.emotionStatus
            ) }
    }

}