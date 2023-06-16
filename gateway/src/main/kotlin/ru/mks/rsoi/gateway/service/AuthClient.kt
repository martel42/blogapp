package ru.mks.rsoi.gateway.service

import org.apache.tomcat.util.http.parser.Authorization
import ru.mks.rsoi.gateway.dto.request.AuthRequest
import ru.mks.rsoi.gateway.dto.response.AuthResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.Header
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(name = "auth", url = "http://localhost:8079/api/v1/auth")
interface AuthClient {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun login(@RequestBody request: AuthRequest): AuthResponse
}