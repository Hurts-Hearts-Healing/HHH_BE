package com.dsm.hhh.internal.core.domain.service.emotion.graph

import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphDetailResponse
import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphResponse
import com.dsm.hhh.internal.core.usecase.emotion.graph.EmotionGraphUseCase
import com.dsm.hhh.internal.data.repository.emotion.emoji.EmotionEmojiRepository
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.EmotionAggregationResult
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
private class EmotionGraphService(
    private val emotionEmojiRepository: EmotionEmojiRepository
) : EmotionGraphUseCase {

    override fun getDailyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusDays(6)

        return emotionEmojiRepository.getEmotionStatsByDayPeriod(startDate, endDate)
            .collectList()
            .map { results ->
                processGraphData(results, "daily")
            }
    }

    override fun getWeeklyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusWeeks(3)

        return emotionEmojiRepository.getEmotionStatsByWeekPeriod(startDate, endDate)
            .collectList()
            .map { results ->
                processDirectWeeklyData(results)
            }
    }

    private fun processDirectWeeklyData(
        results: List<EmotionAggregationResult>
    ): EmotionGraphResponse {

        val groupedByPeriod = results.groupBy { it.date ?: "unknown" }

        val graphData = groupedByPeriod.map { (period, emotionResults) ->
            val emotionCounts = emotionResults.associate { it.emotion to it.count }

            val happiness = calculateHappiness(emotionCounts)
            val status = getHappinessStatus(happiness)

            EmotionGraphDetailResponse(
                period = period,
                happiness = String.format("%.1f", happiness).toDouble(),
                status = status
            )
        }.sortedBy { it.period }

        return EmotionGraphResponse(graphData)
    }

    override fun getMonthlyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusMonths(5)

        return emotionEmojiRepository.getEmotionStatsByMonthPeriod(startDate, endDate)
            .collectList()
            .map { results ->
                processGraphData(results, "monthly")
            }
    }

    private fun calculateHappiness(emotionCounts: Map<String, Int>): Double {
        val happyCount = emotionCounts["HAPPY"] ?: 0
        val sosoCount = emotionCounts["SOSO"] ?: 0
        val sadCount = emotionCounts["SAD"] ?: 0

        val totalCount = happyCount + sosoCount + sadCount

        if (totalCount == 0) {
            return 5.0  // 데이터가 없는 경우 중립적인 값 반환
        }

        val weightedSum = (happyCount * 10.0) + (sosoCount * 5.0) + (sadCount * 0.0)
        return weightedSum / totalCount
    }

    private fun processGraphData(
        results: List<EmotionAggregationResult>,
        type: String
    ): EmotionGraphResponse {
        val groupedByPeriod = results.groupBy { it.date ?: "unknown" }

        val graphData = groupedByPeriod.map { (period, emotionResults) ->
            val emotionCounts = emotionResults.associate { it.emotion to it.count }
            val happiness = calculateHappiness(emotionCounts)
            val status = getHappinessStatus(happiness)

            EmotionGraphDetailResponse(
                period = period,
                happiness = String.format("%.1f", happiness).toDouble(),
                status = status
            )
        }.sortedBy { it.period }

        return EmotionGraphResponse(graphData)
    }

    private fun getHappinessStatus(happiness: Double): String {
        return when {
            happiness >= 8.0 -> "매우 행복"
            happiness >= 6.0 -> "행복"
            happiness >= 4.0 -> "보통"
            happiness >= 2.0 -> "슬픔"
            else -> "매우 슬픔"
        }
    }

}