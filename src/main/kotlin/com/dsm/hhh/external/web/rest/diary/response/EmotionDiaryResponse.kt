package com.dsm.hhh.external.web.rest.diary.response

import com.dsm.hhh.internal.core.domain.model.primitive.diary.CreatedAt
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Note
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Title

class EmotionDiaryResponse(
    val diaryId: DiaryId?,

    val title: Title,
    val note: Note,

    val createdAt: CreatedAt?,
)