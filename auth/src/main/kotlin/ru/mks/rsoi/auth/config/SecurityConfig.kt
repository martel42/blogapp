package ru.mks.rsoi.auth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.mks.rsoi.auth.enums.Permission
import ru.mks.rsoi.auth.enums.Role
import ru.mks.rsoi.auth.util.jwt.CustomUserDetailService
import ru.mks.rsoi.auth.util.jwt.JwtRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired private lateinit var jwtFilter: JwtRequestFilter

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests().requestMatchers(HttpMethod.GET).hasAnyAuthority(
                Permission.USER.authority, Permission.ADMIN.authority)
            .requestMatchers(HttpMethod.POST).anonymous()
            .and()
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

}