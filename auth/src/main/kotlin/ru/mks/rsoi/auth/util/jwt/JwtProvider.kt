package ru.mks.rsoi.auth.util.jwt

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*


@Component
class JwtProvider {
    private val jwtSecret: String = "SAS_SECRET"
    fun generateToken(login: String?): String {
        val date: Date = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getLoginFromToken(token: String?): String {
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
        return claims.subject
    }
}