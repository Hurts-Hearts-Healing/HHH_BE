package com.dsm.hhh.internal.data.repository.diary

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDate

/**
 * EmotionDiaryEntity - Spring Data MongoDB에 의해 영속화되는 감정 일기 도메인 데이터 클래스
 * <p>
 *
 * @property id MongoDB에서 자동 생성되는 고유 식별자. 형식은 ObjectId (Insert 시 null 허용)
 * @property title 감정 일기의 제목
 * @property note 감정 일기의 내용
 * @property createdAt 감정 일기가 작성된 날짜
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-04-07
 * @version 1.0
 */
@Document(collection = "diary-collection")
class EmotionDiaryEntity private constructor(
    @Id
    val id: String?,

    val title: String,

    val note: String,

    @Field(targetType = FieldType.DATE_TIME)
    val createdAt: LocalDate = LocalDate.now()
) {

    constructor(
        title: String,
        note: String
    ) : this(null, title, note)

}