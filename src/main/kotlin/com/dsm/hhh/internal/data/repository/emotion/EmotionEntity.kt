package com.dsm.hhh.internal.data.repository.emotion

import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collation = CollectionSpec.EMOTION)
class EmotionEntity private constructor(
    @Id
    val id: String?,

    val userId: String,

    val emotion: String,

    val createdAt: LocalDate
){

    constructor(
        userId: String,
        emotion: String
    ):this(null, userId, emotion, LocalDate.now())

}