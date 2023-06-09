package ru.mks.rsoi.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mks.rsoi.blog.entity.Blog

@Repository
interface BlogRepository: JpaRepository<Blog, Long>