package ru.mks.rsoi.gateway.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.dto.response.UserSubsResponse

@FeignClient(name = "userSubs", url = "http://localhost:8085/api/v1/userSubs")
interface UserSubsClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserSubsById(@PathVariable id: Long) : UserSubsResponse
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllUserSubs() :List<UserSubsResponse>

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUserSubs(@RequestBody userResponse: UserSubsResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editUserSubs(@RequestBody userResponse: UserSubsResponse)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserSubsById(@PathVariable id: Long)
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllUserSubs()
}