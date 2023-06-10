package ru.mks.rsoi.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.user.entity.Role
import ru.mks.rsoi.user.service.RoleService

@RestController
@CrossOrigin("http://localhost:8082/api/v1/role")
@RequestMapping("api/v1/role")
class RoleController(
        private val roleService: RoleService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm role dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getRoleById(@PathVariable id: Long) : Role =
            roleService.getRoleById(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllRole() :List<Role> =
            roleService.getAllRole()


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addRole(@RequestBody roleResponse: Role) {
        roleService.addOrEditRole(roleResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editRole(@RequestBody roleResponse: Role) {
        roleService.addOrEditRole(roleResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteRoleById(@PathVariable id: Long) {
        roleService.deleteRoleById(id)
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteAllRole() {
        roleService.deleteAllRoles()
    }
}