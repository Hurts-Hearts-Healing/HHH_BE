package com.dsm.hhh.external.web.rest.emotion.analysis.form

import com.dsm.hhh.internal.core.domain.model.primitive.diary.Note
import com.dsm.hhh.internal.core.domain.model.primitive.diary.Title

class EmotionAnalysisRequestForm(
    val title: Title,
    val note: Note
)