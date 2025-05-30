package com.dsm.hhh.internal.data.repository.emotion.emoji

import com.dsm.hhh.external.database.mongo.MongoQueryUtils
import com.dsm.hhh.internal.data.repository.CollectionSpec
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.EmotionAggregationResult
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.EmotionDailyAggregationResult
import com.dsm.hhh.internal.data.repository.emotion.emoji.result.WeeklyEmotionAggregationResult
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators
import org.springframework.data.mongodb.core.aggregation.DateOperators
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDate

@Repository
private class EmotionEmojiRepositoryImpl(
    private val mongoQueryUtils: MongoQueryUtils,
    emotionEmojiMongoRepository: EmotionEmojiMongoRepository
): EmotionEmojiAbstractRepository(emotionEmojiMongoRepository) {

    override fun getEmotionStatsByDayPeriod(startDate: LocalDate, endDate: LocalDate): Flux<EmotionAggregationResult> {
        val aggregation = Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where(EmotionEmojiEntity::createdAt.name)
                    .gte(startDate).lte(endDate)
            ),

            Aggregation.group(EmotionEmojiEntity::createdAt.name, EmotionEmojiEntity::emotion.name)
                .count().`as`("count")
        )

        return mongoQueryUtils.find(
            aggregation,
            CollectionSpec.EMOTION,
            EmotionDailyAggregationResult::class.java
        ).map { result ->
            EmotionAggregationResult(
                emotion = result.id.emotion,
                count = result.count,
                date = result.id.createdAt.toString()
            )
        }
    }

    override fun getEmotionStatsByWeekPeriod(startDate: LocalDate, endDate: LocalDate): Flux<EmotionAggregationResult> {
        val aggregation = Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where(EmotionEmojiEntity::createdAt.name)
                    .gte(startDate).lte(endDate)
            ),

            Aggregation.addFields()
                .addField("weekStart")
                .withValue(
                    DateOperators.DateFromParts.dateFromParts()
                        .year(DateOperators.Year.yearOf(EmotionEmojiEntity::createdAt.name))
                        .month(DateOperators.Month.monthOf(EmotionEmojiEntity::createdAt.name))
                        .day(
                            ArithmeticOperators.Subtract.valueOf(
                                DateOperators.DayOfMonth.dayOfMonth(EmotionEmojiEntity::createdAt.name)
                            ).subtract(
                                ArithmeticOperators.Subtract.valueOf(
                                    DateOperators.DayOfWeek.dayOfWeek(EmotionEmojiEntity::createdAt.name)
                                ).subtract(1)
                            )
                        )
                )
                .build(),

            Aggregation.group("weekStart", EmotionEmojiEntity::emotion.name)
                .count().`as`("count"),

            Aggregation.project("count")
                .and("_id.weekStart").`as`("weekStart")
                .and("_id.${EmotionEmojiEntity::emotion.name}").`as`(EmotionEmojiEntity::emotion.name),

            Aggregation.sort(Sort.by("weekStart"))
        )

        return mongoQueryUtils.find(
            aggregation,
            CollectionSpec.EMOTION,
            WeeklyEmotionAggregationResult::class.java
        ).map { result ->
            EmotionAggregationResult(
                emotion = result.emotion,
                count = result.count,
                date = result.weekStart?.let { weekStart ->
                    val weekEnd = weekStart.plusDays(6)
                    "$weekStart ~ $weekEnd"
                }
            )
        }
    }

    override fun getEmotionStatsByMonthPeriod(startDate: LocalDate, endDate: LocalDate): Flux<EmotionAggregationResult> {
        val aggregation = Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where(EmotionEmojiEntity::createdAt.name)
                    .gte(startDate).lte(endDate)
            ),

            Aggregation.addFields()
                .addField("month")
                .withValue(
                    DateOperators.Month.monthOf(EmotionEmojiEntity::createdAt.name)
                )
                .addField("year")
                .withValue(
                    DateOperators.Year.yearOf(EmotionEmojiEntity::createdAt.name)
                )
                .build(),

            Aggregation.group("year", "month", EmotionEmojiEntity::emotion.name)
                .count().`as`("count"),

            Aggregation.project("count")
                .andExpression("concat(toString(_id.year), '-', toString(_id.month))").`as`("date")
                .and("_id.${EmotionEmojiEntity::emotion.name}").`as`(EmotionEmojiEntity::emotion.name),

            Aggregation.sort(Sort.by("date"))
        )

        return mongoQueryUtils.find(
            aggregation,
            CollectionSpec.EMOTION,
            EmotionAggregationResult::class.java
        )
    }

}