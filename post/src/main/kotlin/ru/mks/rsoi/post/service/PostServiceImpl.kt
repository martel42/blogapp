package ru.mks.rsoi.post.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.post.entity.Post
import ru.mks.rsoi.post.repository.PostRepository

@Service
class PostServiceImpl(
        val postRepository: PostRepository
): PostService {
    @Transactional(readOnly = true)
    override fun getPostById(id: Long): Post =
            postRepository.findById(id).orElseThrow { EntityNotFoundException ("Post $id not found!") }

    @Transactional(readOnly = true)
    override fun getAllPost(): List<Post> =
            postRepository.findAll()

    @Transactional
    override fun addOrEditPost(post: Post) {
        postRepository.save(post)
    }

    @Transactional
    override fun deletePostById(id: Long) {
        postRepository.deleteById(id)
    }

    @Transactional
    override fun deleteAllPost() {
        postRepository.deleteAll()
    }
}