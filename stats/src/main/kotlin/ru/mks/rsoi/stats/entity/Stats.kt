package ru.mks.rsoi.stats.entity

import jakarta.persistence.*
import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

@Entity
@Table(name = "stats")
open class Stats(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val dateRequest: LocalDateTime,
        open val uri: String,
        open val codeStatus: HttpStatusCode
)
