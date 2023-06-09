package ru.mks.rsoi.subs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.subs.entity.UserSubs
import ru.mks.rsoi.subs.service.UserSubsService

@RestController
@CrossOrigin("http://localhost:8085/api/v1/userSubs")
@RequestMapping("api/v1/userSubs")
class UserSubsController(
        private val userSubsService: UserSubsService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm userSubs dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getUserSubsById(@PathVariable id: Long) : UserSubs =
            userSubsService.getUserSubsById(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllUserSubs() :List<UserSubs> =
            userSubsService.getAllUserSubs()


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addUserSubs(@RequestBody userSubsResponse: UserSubs) {
        userSubsService.addOrEditUserSubs(userSubsResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editUserSubs(@RequestBody userSubsResponse: UserSubs) {
        userSubsService.addOrEditUserSubs(userSubsResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteUserSubsById(@PathVariable id: Long) {
        userSubsService.deleteUserSubsById(id)
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteAllUserSubs() {
        userSubsService.deleteAllUserSubs()
    }
}