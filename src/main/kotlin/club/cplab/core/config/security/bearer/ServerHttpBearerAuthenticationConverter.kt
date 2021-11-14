package club.cplab.core.config.security.bearer

import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import org.springframework.http.HttpHeaders
import reactor.core.publisher.Mono

class ServerHttpBearerAuthenticationConverter: ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> = mono {
        val tokenInHeader = exchange?.request?.headers?.getFirst(HttpHeaders.AUTHORIZATION) ?: return@mono null
        if (!tokenInHeader.startsWith("Bearer ")) return@mono null

        return@mono null
    }
}