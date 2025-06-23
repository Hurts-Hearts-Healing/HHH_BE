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
        val messageText = """
            당신은 텍스트 감정 분석 전문가입니다. 
            다음 문장에서 표현된 감정을 한국어로 명확하고 간결하게 분석해 주세요. 
            가능하다면 감정의 정도(예: 매우 슬픔, 약간 기쁨 등)도 함께 설명해 주세요. 

            아래 문장에서 드러나는 감정을 복합적으로 분석해 주세요. 
            주된 감정 외에도 숨겨진 감정이 있다면 함께 설명해 주세요. 

            문장: "$text"
        """.trimIndent()


        val requestBody = mapOf(
            "contents" to listOf(
                mapOf(
                    "parts" to listOf(
                        mapOf("text" to messageText)
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