package com.dsm.hhh.external.web.rest.diary.form

import com.dsm.hhh.internal.core.domain.model.primitive.diary.Note
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Title

class EmotionDiaryWriteRequestForm(
    val title: Title,
    val note: Note,
) {
}