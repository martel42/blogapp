package ru.mks.rsoi.gateway.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.mks.rsoi.gateway.enums.Permission
import ru.mks.rsoi.gateway.jwt.JwtRequestFilter

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
            .authorizeRequests().requestMatchers("user**", "blog**", "subs**", "post")
            .hasAnyAuthority(
                Permission.USER.authority, Permission.ADMIN.authority)
            .requestMatchers(HttpMethod.POST, "login**").anonymous()
            .requestMatchers("stats**").hasAuthority(Permission.ADMIN.authority)
            .and()
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

}