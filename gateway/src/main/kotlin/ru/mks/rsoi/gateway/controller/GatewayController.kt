package ru.mks.rsoi.gateway.controller

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.PostResponse

@RestController
@CrossOrigin("http://localhost:8080/api/v1")
@RequestMapping("api/v1")
class GatewayController {

    @GetMapping("/dummy")
    private fun dummyResponse() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm gateway dummy!", HttpStatus.OK)
    }


}
