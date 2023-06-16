package ru.mks.rsoi.gateway.jwt

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtProvider {
    @Value("\${jwt.secret}")
    private val jwtSecret: String = "SECRET_KEY"
    fun validateToken(token: String?): Boolean {
        return try {
            println(jwtSecret.toByteArray().size)
            val a: SecretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
            val keyJwtSecret = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
            Jwts.parserBuilder().setSigningKey(keyJwtSecret).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            println("sdsd")
            false
        }
    }

    fun getLoginFromToken(token: String?): String {
        val keyJwtSecret = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        val claims: Claims = Jwts.parserBuilder().setSigningKey(keyJwtSecret).build().parseClaimsJws(token).body
        return claims.subject
    }
}