package ru.mks.rsoi.auth.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.auth.dto.UserResponse

@FeignClient(name = "user", url = "http://localhost:8082/api/v1/user")
interface UserClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>
}