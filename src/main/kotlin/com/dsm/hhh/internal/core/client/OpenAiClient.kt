package com.dsm.hhh.internal.core.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OpenAiClient(
    @Value("\${API_KEY}")
    private val apiKey: String
) {
    private val restTemplate = RestTemplate()

    fun analyzeEmotion(content: String): String {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(apiKey)
        }

        val request = mapOf(
            "model" to "gpt-3.5-turbo",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "감정을 분석해줘."),
                mapOf("role" to "user", "content" to content)
            )
        )

        val entity = HttpEntity(request, headers)

        val response = restTemplate.postForEntity(
            "https://api.openai.com/v1/chat/completions",
            entity,
            Map::class.java
        )

        val body = response.body as Map<*, *>
        val choices = body["choices"] as List<*>
        val message = (choices[0] as Map<*, *>)["message"] as Map<*, *>

        return message["content"] as String
    }

    fun summarizeEmotion(content: String): String {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(apiKey)
        }

        val request = mapOf(
            "model" to "gpt-3.5-turbo",
            "messages" to listOf(
                mapOf("role" to "system", "content" to "감정을 한두 문장으로 요약해줘."),
                mapOf("role" to "user", "content" to content)
            )
        )

        val entity = HttpEntity(request, headers)

        val response = restTemplate.postForEntity(
            "https://api.openai.com/v1/chat/completions",
            entity,
            Map::class.java
        )

        val body = response.body as Map<*, *>
        val choices = body["choices"] as List<*>
        val message = (choices[0] as Map<*, *>)["message"] as Map<*, *>

        return message["content"] as String
    }
}

