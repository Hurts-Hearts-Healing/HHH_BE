package com.dsm.hhh.internal.data.repository.user.archive.mapper

import com.dsm.hhh.internal.core.domain.model.dto.user.archive.ArchiveUserInternalDTO
import com.dsm.hhh.internal.data.repository.user.archive.ArchiveUserEntity

class ArchiveUserEntityMapper {

    companion object {

        fun toEntity(archiveUserInternalDTO: ArchiveUserInternalDTO): ArchiveUserEntity {
            return ArchiveUserEntity(
                email = archiveUserInternalDTO.email.value(),
                userId = archiveUserInternalDTO.userId.stream()
                    .map { userId -> userId.value() }
                    .toList()
            )
        }

        fun toEntity(newDTO: ArchiveUserInternalDTO, existingDTO: ArchiveUserInternalDTO): ArchiveUserEntity {
            val existingEntity = ArchiveUserEntity(
                email = existingDTO.email.value(),
                userId = existingDTO.userId.stream()
                    .map { userId -> userId.value() }
                    .toList()
            )
            
            val updatedUserIds = existingEntity.userId.toMutableList()
            updatedUserIds.add(newDTO.userId.get(0).value())
            
            return ArchiveUserEntity(
                email = existingDTO.email.value(),
                userId = updatedUserIds
            )
        }

    }

}