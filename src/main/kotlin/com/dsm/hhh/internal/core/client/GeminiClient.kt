package com.dsm.hhh.internal.core.client

import reactor.core.publisher.Mono

interface GeminiClient {
    fun analyzeEmotion(text: String): Mono<String>
}