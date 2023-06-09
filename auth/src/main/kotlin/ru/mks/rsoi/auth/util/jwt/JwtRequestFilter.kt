package ru.mks.rsoi.auth.util.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException


@Component
class JwtRequestFilter : GenericFilterBean(

) {
    @Autowired
    private lateinit var jwtProvider: JwtProvider

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailService

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val token = getTokenFromRequest(servletRequest as HttpServletRequest)
        if (token != null && jwtProvider.validateToken(token)) {
            val userLogin =  jwtProvider.getLoginFromToken(token)
            val customAdminDetails: CustomUserDetails = customUserDetailsService.loadUserByUsername(userLogin)!!
            val auth = UsernamePasswordAuthenticationToken(customAdminDetails, null, customAdminDetails.authorities)
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader(AUTHORIZATION)
        return if (bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}