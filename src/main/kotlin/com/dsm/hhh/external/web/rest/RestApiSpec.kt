package com.dsm.hhh.external.web.rest

object RestApiSpec {

    const val AUTH_LOGIN = "/api/auth/login"
    const val AUTH_REGISTER = "/api/auth/register"

    const val MAIL_SEND = "/api/mail"
    const val MAIL_VERIFY = "/api/mail/verify"

    const val DIARY_CREATE = "/api/diary"
    const val DIARY_UPDATE = "/api/diary/{id}"
    const val DIARY_ONE = "/api/diary/{id}"
    const val DIARY_ALL = "/api/diary"

    const val USER_DEACTIVATE = "/api/user/deactivate"

    const val EMOTION_CREATE = "/api/emotion"
    const val EMOTION_READ = "/api/emotion"
}