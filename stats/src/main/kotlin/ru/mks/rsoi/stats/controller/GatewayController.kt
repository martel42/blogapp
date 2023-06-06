package ru.mks.rsoi.stats.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:8081/api/v1/stats")
@RequestMapping("api/v1/stats")
class StatsController {
    @GetMapping("/dummy")
    private fun dummyResponse() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm stats dummy!", HttpStatus.OK)
    }

}
