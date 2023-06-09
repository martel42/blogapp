package ru.mks.rsoi.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.user.response.UserResponse
import ru.mks.rsoi.user.service.RoleService
import ru.mks.rsoi.user.service.UserService

@RestController
@CrossOrigin("http://localhost:8081/api/v1/user")
@RequestMapping("api/v1/user")
class UserController(
        private val userService: UserService,
        private val roleService: RoleService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm user dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getUserById(@PathVariable id: Long) : UserResponse {
        val user = userService.getUserById(id)
        val role = roleService.getRoleById(user.roleId)
        return UserResponse( user.UID, user.login, user.password, user.gender,
                user.phoneNumber, user.birthDate, user.registerDate, role)
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllUser() :List<UserResponse> {
        return userService.getAllUser().map { UserResponse(it.UID, it.login, it.password, it.gender, it.phoneNumber,
                it.birthDate, it.registerDate, roleService.getRoleById(it.roleId)) }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addUser(@RequestBody userResponse: UserResponse) {
        userService.addOrEditUser(userResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editUser(@RequestBody userResponse: UserResponse) {
        userService.addOrEditUser(userResponse)
    }
}
