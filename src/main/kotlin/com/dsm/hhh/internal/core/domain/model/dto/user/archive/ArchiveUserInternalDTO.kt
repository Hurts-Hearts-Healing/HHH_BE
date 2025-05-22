package com.dsm.hhh.internal.core.domain.model.dto.user.archive

import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId

class ArchiveUserInternalDTO(
    val email: Email,
    val userId: List<UserId>
)