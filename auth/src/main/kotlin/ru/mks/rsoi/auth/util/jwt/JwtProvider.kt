package ru.mks.rsoi.auth.util.jwt

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.*
import java.util.Base64.Decoder
import javax.crypto.SecretKey


@Component
class JwtProvider {
    @Value("\${jwt.secret}")
    private val jwtSecret: String = "SECRET_KEY"
    fun generateToken(login: String?): String {
        val date: Date = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant())
        val keyJwtSecret = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(keyJwtSecret)
                .compact()
    }

    fun validateToken(token: String?): Boolean {
        return try {
            val a: SecretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
            val keyJwtSecret = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
            Jwts.parserBuilder().setSigningKey(keyJwtSecret).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getLoginFromToken(token: String?): String {
        val keyJwtSecret = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        val claims: Claims = Jwts.parserBuilder().setSigningKey(keyJwtSecret).build().parseClaimsJws(token).body
        return claims.subject
    }
}