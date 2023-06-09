package service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.UserResponse

@FeignClient(name = "user", url = "http://localhost:8082/api/v1/user")
interface UserClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(@PathVariable id: Long) : UserResponse
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
     fun getAllUser() :List<UserResponse>

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody userResponse: UserResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editUser(@RequestBody userResponse: UserResponse)
}