package ru.mks.rsoi.user.service

import ru.mks.rsoi.user.entity.User
import ru.mks.rsoi.user.response.UserResponse

interface UserService {
    fun getUserById(id: Long): User
    fun getAllUser(): List<User>
    fun addOrEditUser(user: UserResponse)
}