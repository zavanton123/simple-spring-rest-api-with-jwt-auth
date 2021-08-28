package com.evolunta.api.auth.config.jwt

import com.evolunta.api.auth.data.model.User
import com.evolunta.api.auth.exception.TokenParseException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtTokenUtil(
    private val log: Logger,
    @Value("\${JWT_SECRET}")
    private val jwtSecret: String,
    @Value("\${JWT_ISSUER}")
    private val jwtIssuer: String,
) {

    companion object {
        private const val WEEK_IN_MILLISEC = 7 * 24 * 60 * 60 * 1000
    }

    fun generateAccessToken(user: User): String {
        return Jwts.builder()
            .setSubject("${user.id},${user.username}")
            .setIssuer(jwtIssuer)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + WEEK_IN_MILLISEC))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun extractUserId(token: String): String {
        return extractJwtTokenPart(token, 0)
    }

    fun extractUsername(token: String): String {
        return extractJwtTokenPart(token, 1)
    }

    fun extractExpirationDate(token: String): Date {
        return parseJwtToken(token).expiration
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (exception: Exception) {
            log.error("zavanton - Failed to validate token: $token ${exception.message}")
        }
        return false
    }

    private fun extractJwtTokenPart(token: String, index: Int): String {
        return parseJwtToken(token)
            .subject
            .split(",")[index]
    }

    private fun parseJwtToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body ?: throw TokenParseException("Failed to parse token $token")
    }
}
