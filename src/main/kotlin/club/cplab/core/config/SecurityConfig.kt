package club.cplab.core.config

import club.cplab.core.config.security.bearer.BearerTokenReactiveAuthenticationManager
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        val excludePath = arrayOf(
            "/**"
        )
        return http
            .cors().and()
            .csrf().disable()
            .logout().disable()
            .httpBasic().disable()
            .authorizeExchange()
            .pathMatchers(*excludePath).permitAll()
            .and()
            .authorizeExchange()
            .pathMatchers("/**")
            .authenticated()
            .and()
            //.addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    fun bearerAuthenticationFilter(): AuthenticationWebFilter {
        val authManager = BearerTokenReactiveAuthenticationManager()
        val authFilter = AuthenticationWebFilter(authManager)
        authFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/api/**"))
        return authFilter
    }
}