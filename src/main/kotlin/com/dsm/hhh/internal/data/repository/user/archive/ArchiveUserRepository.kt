package com.dsm.hhh.internal.data.repository.user.archive

import com.dsm.hhh.internal.core.domain.model.dto.user.archive.ArchiveUserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import reactor.core.publisher.Mono

interface ArchiveUserRepository {

    fun save(archiveUserInternalDTO: ArchiveUserInternalDTO): Mono<ArchiveUserInternalDTO>

    fun save(archiveUserInternalDTO: ArchiveUserInternalDTO, existingArchiveUser: ArchiveUserInternalDTO): Mono<ArchiveUserInternalDTO>

    fun findByEmail(email: Email): Mono<ArchiveUserInternalDTO>

}