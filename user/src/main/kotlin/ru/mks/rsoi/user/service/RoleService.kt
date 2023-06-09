package ru.mks.rsoi.user.service

import ru.mks.rsoi.user.entity.Role


interface RoleService {
    fun getRoleById(id: Long): Role
    fun getAllRole(): List<Role>
    fun addOrEditRole(role: Role)
    fun deleteRoleById(id: Long)
    fun deleteAllRoles()
}