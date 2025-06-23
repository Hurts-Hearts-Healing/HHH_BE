package com.dsm.hhh.internal.core.client

import com.dsm.hhh.external.web.client.WebClientRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GeminiClientImpl(
    private val webClientRequest: WebClientRequest,

    @Value("\${google.gemini.api.key}")
    private val apiKey: String,

    @Value("\${google.gemini.api.uri}")
    private val apiUri: String
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

        return webClientRequest.post(apiUri + apiKey, requestBody)
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