package com.dsm.hhh.external.web.rest.user.response

import com.dsm.hhh.internal.core.domain.model.primitive.user.Birthday
import com.dsm.hhh.internal.core.domain.model.primitive.user.BreakupDate
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.EmotionStatus
import com.dsm.hhh.internal.core.domain.model.primitive.user.Name

class UserInfoResponse(
    val name: Name,
    val email: Email,
    val birthday: Birthday,
    val breakupDate: BreakupDate,
    val emotionStatus: EmotionStatus
)