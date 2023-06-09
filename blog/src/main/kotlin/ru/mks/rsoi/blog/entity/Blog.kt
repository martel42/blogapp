package ru.mks.rsoi.blog.entity

import jakarta.persistence.*
import org.springframework.http.HttpStatusCode
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "blog")
open class Blog(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val userUID: Long,
        open val nameBlog: String,
        open val description: String,
        open val creationDate: LocalDate
)
