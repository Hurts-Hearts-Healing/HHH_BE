package com.dsm.hhh.external.security

import com.dsm.hhh.external.web.rest.RestApiSpec
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

/**
 * SecurityConfig - Spring Security 설정
 * <p>
 * 웹 애플리케이션의 보안 설정을 정의합니다.
 * Spring WebFlux 환경에 맞는 설정을 제공합니다.
 * </p>
 *
 * @author Kim Seung Won
 * @since 2025-05-11
 * @version 1.0
 */
@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val pasetoAuthenticationFilter: PasetoAuthenticationFilter
) {

    /**
     * 보안 필터 체인 설정
     * @param http ServerHttpSecurity 설정 객체
     * @return 설정된 SecurityWebFilterChain
     */
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .cors { corsConfigurationSource() }
            .csrf { csrf -> csrf.disable() }
            .formLogin { formLogin -> formLogin.disable() }
            .httpBasic { httpBasic -> httpBasic.disable() }
            .logout { logout -> logout.disable() }
            .authorizeExchange { authorizeExchangeSpec -> authorizeExchangeSpec
                .pathMatchers(HttpMethod.POST, RestApiSpec.AUTH_LOGIN, RestApiSpec.AUTH_REGISTER).permitAll()
                .anyExchange().authenticated()
            }
            .headers { headers -> headers.frameOptions { frameOptionsSpec -> frameOptionsSpec.disable()} }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .addFilterBefore(pasetoAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    /**
     * CORS 설정
     * @return CORS 설정 소스
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
        configuration.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

} 