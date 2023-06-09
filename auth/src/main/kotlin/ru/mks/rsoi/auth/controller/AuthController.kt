package ru.mks.rsoi.auth.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mks.rsoi.auth.service.UserClient

@RestController
@CrossOrigin("http://localhost:8079/api/v1/auth")
@RequestMapping("api/v1/auth")
class AuthController(
        val userClient: UserClient
) {
    @GetMapping("/dummy")
    private fun dummyResponse() : ResponseEntity<String> {
        return userClient.dummyRequest()
    }
}