package ru.mks.rsoi.blog.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.blog.entity.Blog
import ru.mks.rsoi.blog.repository.BlogRepository

@Service
class BlogServiceImpl(
        val blogRepository: BlogRepository
): BlogService {
    @Transactional(readOnly = true)
    override fun getBlogById(id: Long): Blog =
            blogRepository.findById(id).orElseThrow { EntityNotFoundException ("Blog $id not found!") }

    @Transactional(readOnly = true)
    override fun getAllBlog(): List<Blog> =
            blogRepository.findAll()

    @Transactional
    override fun addOrEditBlog(blog: Blog) {
        blogRepository.save(blog)
    }

    @Transactional
    override fun deleteBlogById(id: Long) {
        blogRepository.deleteById(id)
    }

    @Transactional
    override fun deleteAllBlog() {
        blogRepository.deleteAll()
    }
}