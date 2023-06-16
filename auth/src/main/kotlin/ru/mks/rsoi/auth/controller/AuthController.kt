package ru.mks.rsoi.auth.controller

import jakarta.persistence.EntityNotFoundException
import jdk.jshell.spi.ExecutionControl.UserException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.auth.dto.AuthRequest
import ru.mks.rsoi.auth.dto.AuthResponse
import ru.mks.rsoi.auth.dto.UserResponse
import ru.mks.rsoi.auth.enums.Permission
import ru.mks.rsoi.auth.service.UserClient
import ru.mks.rsoi.auth.util.jwt.JwtProvider


@RestController
@CrossOrigin("http://localhost:8079/api/v1/auth")
@RequestMapping("api/v1/auth")
class AuthController(
        val userClient: UserClient,
        val jwtProvider: JwtProvider
) {
    @GetMapping("/dummy")
    private fun dummyResponse() : ResponseEntity<String> {
        return userClient.dummyRequest()
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun auth(@RequestBody request: AuthRequest): AuthResponse {
        val userList: List<UserResponse> = userClient.getAllUser()
        val user = userList.find { it.login == request.login && it.password == request.password }
            ?: throw EntityNotFoundException("User Not found!")
        val token: String = jwtProvider.generateToken(user.login)
        return AuthResponse(token)
    }
}