package com.dsm.hhh.external.database.mongo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class MongoQueryUtils(
    private val reactiveMongoTemplate: ReactiveMongoTemplate
) {

    fun <T> find(aggregation: Aggregation, collectionName: String, targetClass: Class<T>): Flux<T> {
        return reactiveMongoTemplate.aggregate(
            aggregation,
            collectionName,
            targetClass
        )
    }

}