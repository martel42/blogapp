package ru.mks.rsoi.user.response

import ru.mks.rsoi.user.entity.Role
import java.time.LocalDate

data class UserResponse(
    val uid: Long,
    val login: String,
    val password: String,
    val gender: Boolean,
    val phoneNumber: Int,
    val birthDate: LocalDate,
    val registerDate: LocalDate,
    val role: Role
)
