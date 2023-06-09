package ru.mks.rsoi.gateway.response

import java.time.LocalDate


open class BlogResponse(
        open val id: Long,
        open val userUID: Long,
        open val nameBlog: String,
        open val description: String,
        open val creationDate: LocalDate
)
