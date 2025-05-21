package com.dsm.hhh.external.web.rest

object RestApiSpec {

    const val AUTH = "/api/auth"
    const val AUTH_APIS = "/api/auth/**"
    const val AUTH_LOGIN = "/api/auth/login"
    const val AUTH_REGISTER = "/api/auth/register"

    const val DIARY = "/api/diary"
    const val DIARY_APIS = "/api/diary/**"
    const val DIARY_CREATE = "/api/diary"
    const val DIARY_UPDATE = "/api/diary/{id}"
    const val DIARY_ONE = "/api/diary/{id}"
    const val DIARY_ALL = "/api/diary"

}