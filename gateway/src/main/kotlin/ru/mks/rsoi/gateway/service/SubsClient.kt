package ru.mks.rsoi.gateway.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.SubsResponse

@FeignClient(name = "subs", url = "http://localhost:8085/api/v1/subs")
interface SubsClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getSubsById(@PathVariable id: Long) : SubsResponse
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllSubs() :List<SubsResponse>

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addSubs(@RequestBody userResponse: SubsResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editSubs(@RequestBody userResponse: SubsResponse)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSubsById(@PathVariable id: Long)
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllSubs()
}