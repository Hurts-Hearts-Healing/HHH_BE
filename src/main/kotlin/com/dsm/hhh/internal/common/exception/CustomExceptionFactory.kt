package com.dsm.hhh.internal.common.exception

import com.dsm.hhh.external.error.ErrorCode
import com.dsm.hhh.external.exception.CustomException
import com.dsm.hhh.external.exception.ExceptionProperty

class CustomExceptionFactory private constructor() {

    companion object {

        fun fromThrowable(throwable: Throwable): CustomException {
            if (throwable is CustomException) {
                return throwable
            }

            return CustomException(500, ExceptionProperty(
                "CEF_001",
                throwable.message ?: "메세지가 존재하지 않습니다."
            ))
        }

        fun badRequest(error: ErrorCode): CustomException =
            CustomException(400, ExceptionProperty(error.errorCode, error.message))

        fun badRequest(errorCode: String, message: String): CustomException =
            CustomException(400, ExceptionProperty(errorCode, message))

        fun unauthorized(error: ErrorCode): CustomException =
            CustomException(401, ExceptionProperty(error.errorCode, error.message))

        fun notFound(error: ErrorCode): CustomException =
            CustomException(404, ExceptionProperty(error.errorCode, error.message))

        fun conflict(error: ErrorCode): CustomException =
            CustomException(409, ExceptionProperty(error.errorCode, error.message))

        fun internalServerError(error: ErrorCode): CustomException =
            CustomException(500, ExceptionProperty(error.errorCode, error.message))

        fun serviceUnavailable(error: ErrorCode): CustomException =
            CustomException(503, ExceptionProperty(error.errorCode, error.message))

    }

}