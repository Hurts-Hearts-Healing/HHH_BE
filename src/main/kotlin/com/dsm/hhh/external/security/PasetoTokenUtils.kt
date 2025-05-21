package com.dsm.hhh.external.security

import dev.paseto.jpaseto.Claims
import dev.paseto.jpaseto.Paseto
import dev.paseto.jpaseto.Pasetos
import dev.paseto.jpaseto.lang.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.KeyPair
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey

/**
 * PasetoTokenUtils - PASETO 토큰 유틸리티
 * <p>
 * Platform-Agnostic Security Tokens (PASETO) 생성 및 검증을 위한 유틸리티
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-08
 * @version 1.0
 */
@Component
class PasetoTokenUtils(
    @Value("\${security.paseto.token-expiration-hours:24}") private val tokenExpirationHours: Long
) {

    companion object {
        private val keyPair: KeyPair = Keys.keyPairFor(dev.paseto.jpaseto.Version.V2)
        private val secretKey: SecretKey = Keys.secretKey()
    }

    /**
     * 사용자 ID를 기반으로 PASETO 토큰 생성
     * @param userId 사용자 ID
     * @return 생성된 PASETO 토큰
     */
    fun generateToken(userId: String): Mono<String> {
        return Mono.fromCallable {
            val now = Instant.now()
            val expiresAt = now.plus(tokenExpirationHours, ChronoUnit.HOURS)

            Pasetos.V2.LOCAL.builder()
                .setSharedSecret(secretKey)
                .setKeyId(UUID.randomUUID().toString())
                .setIssuer("hhh-auth-service")
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .compact()
        }
    }

    /**
     * PASETO 토큰 검증
     * @param token 검증할 PASETO 토큰
     * @return 검증 결과 및 클레임 정보
     */
    fun validateToken(token: String): Mono<Boolean> {
        return parseToken(token)
            .map { paseto -> paseto.claims.expiration.isAfter(Instant.now()) }
            .onErrorResume { Mono.just(false) }
    }

    fun parseClaims(token: String): Mono<Claims> {
        return parseToken(token)
            .map { paseto -> paseto.claims }
    }

    fun parseToken(token: String): Mono<Paseto> {
        return Mono.fromCallable {
            val parser = Pasetos.parserBuilder()
                .setSharedSecret(secretKey)
                .setPublicKey(keyPair.public)
                .build()

            parser.parse(token)
        }
    }

    /**
     * 토큰에서 사용자 ID 추출
     * @param token PASETO 토큰
     * @return 사용자 ID
     */
    fun getUserIdFromToken(token: String): Mono<String> {
        return parseToken(token)
            .map { paseto -> paseto.claims.subject }
    }

} 