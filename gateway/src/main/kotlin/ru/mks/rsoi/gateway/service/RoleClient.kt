package ru.mks.rsoi.gateway.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.RoleResponse

@FeignClient(name = "role", url = "http://localhost:8082/api/v1/role")
interface RoleClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getRoleById(@PathVariable id: Long) : RoleResponse
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllRole() :List<RoleResponse>

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addRole(@RequestBody userResponse: RoleResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editRole(@RequestBody userResponse: RoleResponse)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRoleById(@PathVariable id: Long)
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllRole()
}