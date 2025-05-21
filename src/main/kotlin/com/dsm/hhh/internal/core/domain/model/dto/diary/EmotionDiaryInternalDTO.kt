package com.dsm.hhh.internal.core.domain.model.dto.diary

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Note
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Title
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId

/**
 * EmotionDiaryEntity - 계층별 데이터 변환을 위한 감정 일기 도메인 데이터 클래스
 * <p>
 *
 * @property diaryId MongoDB에서 자동 생성되는 고유 식별자. 형식은 ObjectId (Insert 시 null 허용)
 * @property title 감정 일기의 제목
 * @property note 감정 일기의 내용
 * @property createdAt 감정 일기가 작성된 날짜
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-04-21
 * @version 1.0
 */
class EmotionDiaryInternalDTO(
    val diaryId: DiaryId?,

    val title: Title,
    val note: Note,

    val createdAt: CreatedAt?,

    var userId: UserId?
) {

    constructor(
        title: Title,
        note: Note
    ): this(null, title, note, null, null)

}