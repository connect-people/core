package club.cplab.core.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JWTService {

    private val ONE_DAY = 1000 * 60 * 60 * 24
    private val secretKey = "ullee"

    fun generateToken(): String {
        return JWT.create()
            .sign(Algorithm.HMAC256(secretKey.toByteArray()))
    }

    fun decode(accessToken: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC256(secretKey))
            .build().verify(accessToken)
    }

    fun getAuthentication(accessToken: String): Authentication {
        val decodedJWT = decode(accessToken)
        val principal = decodedJWT.subject
        return UsernamePasswordAuthenticationToken(principal, null, null)
    }
}