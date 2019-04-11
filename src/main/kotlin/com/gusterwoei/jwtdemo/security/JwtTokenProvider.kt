package com.gusterwoei.jwtdemo.security

import com.gusterwoei.jwtdemo.errors.InvalidJwtAuthenticationException
import javax.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import java.util.*
import kotlin.collections.HashMap


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@Component
class JwtTokenProvider {

    private var secretKey = Keys.hmacShaKeyFor(Base64Utils.decodeFromString("SE1BQyBhbmQgcGFzc3dvcmQgaGFzaGluZyBzb2x2ZSB2ZXJ5IGRpZmZlcmVudCBwcm9ibGVtcy4gV2l0aCBITUFDIHRoZSBzZWNyZXQgaXMgZXhwZWN0ZWQgdG8gYmUgaGlnaCBxdWFsaXR5IChpZSAxMjggYml0cyBvZiBlbnRyb3B5KSwgd2hlcmVhcyBwYXNzd29yZHMgYXJlIGV4cGVjdGVkIHRvIGJlIGxvdyBxdWFsaXR5ICgzMCBiaXRzIG9mIGVudHJvcHkgaXMgb3B0aW1pc3RpYyBmb3IgbW9zdCBwZW9wbGUpLg=="))

    //@Value("\${security.jwt.token.expire-length:3600000}")
    private val validityInMilliseconds: Long = 5 * 60 * 1000

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    fun createToken(username: String, roles: List<String>): String {
        val user = hashMapOf<String, Any>().apply {
            put("id", 30001)
            put("name", username)
            put("role", "admin")
        }

        val claims = Jwts.claims().apply {
            this.subject = "user info"
            this.put("user", user)
        }

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        val accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact()

        // create a refresh token for this access token

        return accessToken
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = this.userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            val user = claims.body.get("user") as HashMap<*, *>
            user.get("userId")

            return !claims.body.expiration.before(Date())

        } catch (e: JwtException) {
            throw InvalidJwtAuthenticationException("Expired or invalid JWT token")
        } catch (e: IllegalArgumentException) {
            throw InvalidJwtAuthenticationException("Expired or invalid JWT token")
        }

    }

}