package com.dsm.hhh.external.exception

import com.dsm.hhh.internal.common.exception.CustomExceptionFactory
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Component
@Order(-2)
class ExceptionHandler(
    private val objectMapper: ObjectMapper
) : WebExceptionHandler {

    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    override fun handle(exchange: ServerWebExchange, throwable: Throwable): Mono<Void> {
        logger.info(throwable.javaClass.simpleName, exchange.request.path)

        if (exchange.response.isCommitted) {
            logger.warn(throwable.message)
            return Mono.error(throwable)
        }

        val customException = CustomExceptionFactory.fromThrowable(throwable)
        val errorProperty = customException.property

        logger.error(
            "Status={}, ErrorCode={}, Message='{}', ExceptionType='{}'",
            customException.statusCode,
            errorProperty.errorCode,
            errorProperty.message,
            throwable.javaClass.name,
            if (throwable !is CustomException) throwable else null
        )

        val response = exchange.response
        response.statusCode = HttpStatus.resolve(customException.statusCode) ?: HttpStatus.INTERNAL_SERVER_ERROR
        response.headers.contentType = MediaType.APPLICATION_JSON

        return response.writeWith(
            Mono.fromCallable {
                objectMapper.writeValueAsBytes(errorProperty)
            }.map { bytes ->
                response.bufferFactory().wrap(bytes)
            }
        )
    }

}