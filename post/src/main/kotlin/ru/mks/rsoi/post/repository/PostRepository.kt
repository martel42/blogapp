package ru.mks.rsoi.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mks.rsoi.post.entity.Post

@Repository
interface PostRepository: JpaRepository<Post, Long>