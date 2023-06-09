package ru.mks.rsoi.user.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "userss")
open class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val UID: Long,
        open val login: String,
        open val password: String,
        open val gender: Boolean,
        open val phoneNumber: Int,
        open val birthDate: LocalDate,
        open val registerDate: LocalDate,
        open val roleId: Long
)
