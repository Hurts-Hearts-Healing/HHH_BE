package com.dsm.hhh.internal.data.repository.emotion

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.CollectionSpec
import com.dsm.hhh.internal.data.repository.user.UserEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collation = CollectionSpec.EMOTION)
class EmotionEntity private constructor(
    @Id
    val id: String?,

    val userId: String,

    val emotion: String,

    val createdAt: CreatedAt?
){
    constructor(
        userId: String,
        emotion: String
    ):this(null, userId, emotion, null)
}