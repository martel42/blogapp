package ru.mks.rsoi.course.controller

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:8080/api/v1")
@RequestMapping("api/v1")
class GatewayController {
    @GetMapping("/dummy")
    private fun dummyResponse() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm dummy!", HttpStatus.OK)
    }
}
