package com.dsm.hhh.internal.core.domain.service.emotion.graph

import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphDetailResponse
import com.dsm.hhh.external.web.rest.emotion.graph.response.EmotionGraphResponse
import com.dsm.hhh.internal.core.domain.component.emotion.graph.HappinessCalculator
import com.dsm.hhh.internal.core.usecase.emotion.graph.EmotionGraphUseCase
import com.dsm.hhh.internal.data.repository.emotion.emoji.EmotionEmojiRepository
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.EmotionAggregationResult
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
private class EmotionGraphService(
    private val emotionEmojiRepository: EmotionEmojiRepository
) : EmotionGraphUseCase {

    override fun getDailyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusDays(6)

        return getEmotionGraph(
            dataFetcher = { emotionEmojiRepository.getEmotionStatsByDayPeriod(startDate, endDate) }
        )
    }

    override fun getWeeklyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusWeeks(3)

        return getEmotionGraph(
            dataFetcher = { emotionEmojiRepository.getEmotionStatsByWeekPeriod(startDate, endDate) }
        )
    }

    override fun getMonthlyEmotionGraph(): Mono<EmotionGraphResponse> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusMonths(5)

        return getEmotionGraph(
            dataFetcher = { emotionEmojiRepository.getEmotionStatsByMonthPeriod(startDate, endDate) }
        )
    }

    private fun getEmotionGraph(
        dataFetcher: () -> Flux<EmotionAggregationResult>
    ): Mono<EmotionGraphResponse> {
        return dataFetcher()
            .collectList()
            .map { results -> buildGraphResponse(results) }
    }

    private fun buildGraphResponse(
        results: List<EmotionAggregationResult>
    ): EmotionGraphResponse {
        val groupedByPeriod = results.groupBy { it.date ?: "unknown" }

        val graphData = groupedByPeriod.map { (period, emotionResults) ->
            val emotionCounts = emotionResults.associate { it.emotion to it.count }
            val happiness = HappinessCalculator.calculateHappiness(emotionCounts)
            val status = HappinessCalculator.getHappinessStatus(happiness)

            EmotionGraphDetailResponse(
                period = period,
                happiness = String.format("%.1f", happiness).toDouble(),
                status = status
            )
        }.sortedBy { it.period }

        return EmotionGraphResponse(graphData)
    }

}