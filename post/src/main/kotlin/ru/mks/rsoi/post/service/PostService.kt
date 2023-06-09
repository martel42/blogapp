package ru.mks.rsoi.post.service

import ru.mks.rsoi.post.entity.Post


interface PostService {
    fun getPostById(id: Long): Post
    fun getAllPost(): List<Post>
    fun addOrEditPost(role: Post)
    fun deletePostById(id: Long)
    fun deleteAllPost()
}