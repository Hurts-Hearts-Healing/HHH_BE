package com.dsm.hhh.external.security

import dev.paseto.jpaseto.PasetoException
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

/**
 * PasetoAuthenticationFilter - PASETO 토큰 인증 필터
 * <p>
 * PASETO 토큰을 검증하고 인증 컨텍스트를 설정하는 웹 필터
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-08
 * @version 1.0
 */
@Component
class PasetoAuthenticationFilter(
    private val pasetoTokenUtils: PasetoTokenUtils
) : WebFilter {

    companion object {
        private const val TOKEN_PREFIX = "Bearer "
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = extractToken(exchange.request)

        return if (token != null) {
            try {
                val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))

                pasetoTokenUtils.parseClaims(token)
                    .map { claims ->
                        UsernamePasswordAuthenticationToken(claims.subject, null, authorities)
                    }
                    .flatMap { authentication ->
                        chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                    }
            } catch (e: PasetoException) {
                chain.filter(exchange)
            }
        } else {
            chain.filter(exchange)
        }
    }

    /**
     * 요청 헤더에서 토큰 추출
     * @param request 서버 HTTP 요청
     * @return 추출된 토큰 또는 null
     */
    private fun extractToken(request: ServerHttpRequest): String? {
        val authHeader = request.headers.getFirst(HttpHeaders.AUTHORIZATION) ?: return null

        return if (authHeader.startsWith(TOKEN_PREFIX)) {
            authHeader.substring(TOKEN_PREFIX.length)
        } else {
            null
        }
    }
} 