package ru.mks.rsoi.gateway.dto.response


import org.springframework.http.HttpStatusCode
import java.time.LocalDate
import java.time.LocalDateTime

data class UserResponse(
         val uid: Long,
         val login: String,
         val password: String,
         val gender: Boolean,
         val phoneNumber: Int,
         val birthDate: LocalDate,
         val registerDate: LocalDate,
         val role: RoleResponse
)
