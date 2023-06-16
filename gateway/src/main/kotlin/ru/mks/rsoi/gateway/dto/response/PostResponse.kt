package ru.mks.rsoi.gateway.dto.response

import java.time.LocalDateTime


open class PostResponse(

        open val id: Long,
        open val blogId: Long,
        open val title: String,
        open val content: String,
        open val postDate: LocalDateTime,
        open val premium: Boolean
)
