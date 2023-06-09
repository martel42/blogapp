package ru.mks.rsoi.blog.service

import ru.mks.rsoi.blog.entity.Blog


interface BlogService {
    fun getBlogById(id: Long): Blog
    fun getAllBlog(): List<Blog>
    fun addOrEditBlog(role: Blog)
    fun deleteBlogById(id: Long)
    fun deleteAllBlog()
}