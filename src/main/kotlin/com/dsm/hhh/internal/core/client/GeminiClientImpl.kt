package com.dsm.hhh.internal.core.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.http.MediaType

@Component
class GeminiClientImpl(
    private val webClient: WebClient,

    @Value("\${google.gemini.api.key}")
    private val apiKey: String
) : GeminiClient {

    override fun analyzeEmotion(text: String): Mono<String> {
        val requestBody = mapOf(
            "contents" to listOf(
                mapOf(
                    "parts" to listOf(
                        mapOf("text" to "다음 문장의 감정을 분석해줘: $text")
                    )
                )
            )
        )

        return webClient.post()
            .uri("https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=$apiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map::class.java)
            .map { response ->
                val candidates = response["candidates"] as? List<*> ?: return@map "감정 분석 실패"
                val first = candidates.firstOrNull() as? Map<*, *>
                val content = first?.get("content") as? Map<*, *>
                val parts = content?.get("parts") as? List<*>
                val text = (parts?.firstOrNull() as? Map<*, *>)?.get("text") as? String
                text ?: "감정 분석 결과 없음"
            }
    }
}