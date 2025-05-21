package com.dsm.hhh.external.exception

class CustomException(
    val statusCode: Int,
    val property: ExceptionProperty
): RuntimeException(property.message)