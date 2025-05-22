package com.dsm.hhh.internal.data.repository.user.archive

import com.dsm.hhh.internal.data.repository.CollectionSpec
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = CollectionSpec.ARCHIVE_USER)
class ArchiveUserEntity(
    @Id
    val id: String?,

    @Indexed(unique = true)
    val email: String,
    val userId: List<String> // TODO: 순서 유지. [0]: Ex-1, [1]: Ex-2, [2]: Ex-3
) {

    constructor(
        email: String,
        userId: List<String>
    ): this(null, email, userId)

}