package com.dsm.hhh.internal.core.domain.component.mail

import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Component
class CompletedEmailVerificationComponent {
    private val completedEmailVerificationStorage = ConcurrentHashMap<String, String>()

    fun isCompletedEmail(email: Email): Mono<Boolean> {
        if (completedEmailVerificationStorage.contains(email.value())) {
            return Mono.just(false)
        }
        
        return Mono.just(true)
    }

    fun saveCompletedEmail(email: Email) {
        completedEmailVerificationStorage.put(email.value(), "")
    }
    
}