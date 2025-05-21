package com.dsm.hhh.external.exception

import java.time.LocalDateTime

data class ExceptionProperty(
    val errorCode: String,

    val message: String,

    val timestamp: String
) {

    constructor(
        errorCode: String,
        message: String
    ): this(errorCode, message, LocalDateTime.now().toString())

}