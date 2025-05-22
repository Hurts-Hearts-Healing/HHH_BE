package com.dsm.hhh.internal.data.repository.user.archive

import com.dsm.hhh.internal.core.domain.model.dto.user.archive.ArchiveUserInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.user.Email
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.user.archive.mapper.ArchiveUserEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
private class ArchiveUserRepositoryImpl(
    private val archiveUserMongoRepository: ArchiveUserMongoRepository
): ArchiveUserRepository {

    override fun save(archiveUserInternalDTO: ArchiveUserInternalDTO): Mono<ArchiveUserInternalDTO> {
        val entity = ArchiveUserEntityMapper.toEntity(archiveUserInternalDTO)

        return archiveUserMongoRepository.save(entity)
            .thenReturn(archiveUserInternalDTO)
    }

    override fun save(
        archiveUserInternalDTO: ArchiveUserInternalDTO,
        existingArchiveUser: ArchiveUserInternalDTO
    ): Mono<ArchiveUserInternalDTO> {
        val updatedEntity = ArchiveUserEntityMapper.toEntity(archiveUserInternalDTO, existingArchiveUser)

        return archiveUserMongoRepository.save(updatedEntity)
            .thenReturn(archiveUserInternalDTO)
    }

    override fun findByEmail(email: Email): Mono<ArchiveUserInternalDTO> {
        return archiveUserMongoRepository.findByEmail(email.value())
            .map { entity ->
                ArchiveUserInternalDTO(
                    email = email,
                    userId = entity.userId.stream()
                        .map { userId -> UserId(userId) }
                        .toList()
                )
            }
            .switchIfEmpty(Mono.empty())
    }

}