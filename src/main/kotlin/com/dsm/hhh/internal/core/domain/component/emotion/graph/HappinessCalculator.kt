package com.dsm.hhh.internal.core.domain.component.emotion.graph

class HappinessCalculator {

    companion object {

        fun calculateHappiness(emotionCounts: Map<String, Int>): Double {
            val happyCount = emotionCounts["HAPPY"] ?: 0
            val sosoCount = emotionCounts["SOSO"] ?: 0
            val sadCount = emotionCounts["SAD"] ?: 0

            val totalCount = happyCount + sosoCount + sadCount

            if (totalCount == 0) {
                return 5.0
            }

            val weightedSum = (happyCount * 10.0) + (sosoCount * 5.0) + (sadCount * 0.0)
            return weightedSum / totalCount
        }

        fun getHappinessStatus(happiness: Double): String {
            return when {
                happiness >= 8.0 -> "매우 행복"
                happiness >= 6.0 -> "행복"
                happiness >= 4.0 -> "보통"
                happiness >= 2.0 -> "슬픔"
                else -> "매우 슬픔"
            }
        }

    }

}