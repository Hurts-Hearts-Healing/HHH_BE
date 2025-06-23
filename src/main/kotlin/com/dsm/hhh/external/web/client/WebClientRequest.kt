package com.dsm.hhh.external.web.client

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WebClientRequest(
    private val webClient: WebClient
) {

    fun post(url: String, requestBody: Map<String, *>): Mono<Map<*, *>> = webClient
        .post()
        .uri(url)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(Map::class.java)

}