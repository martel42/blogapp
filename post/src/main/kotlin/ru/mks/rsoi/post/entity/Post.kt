package ru.mks.rsoi.post.entity

import jakarta.persistence.*
import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

@Entity
@Table(name = "post")
open class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val blogId: Long,
        open val title: String,
        open val content: String,
        open val postDate: LocalDateTime,
        open val premium: Boolean
)
