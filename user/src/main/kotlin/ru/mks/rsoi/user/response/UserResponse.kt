package ru.mks.rsoi.user.response

import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.springframework.http.HttpStatusCode
import ru.mks.rsoi.user.entity.Role
import java.time.LocalDate
import java.time.LocalDateTime

data class UserResponse(
         val UID: Long,
         val login: String,
         val password: String,
         val gender: Boolean,
         val phoneNumber: Int,
         val birthDate: LocalDate,
         val registerDate: LocalDate,
         val role: Role
)
