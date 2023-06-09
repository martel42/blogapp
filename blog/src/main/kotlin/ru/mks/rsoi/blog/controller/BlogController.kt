package ru.mks.rsoi.blog.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.blog.entity.Blog
import ru.mks.rsoi.blog.service.BlogService

@RestController
@CrossOrigin("http://localhost:8083/api/v1/blog")
@RequestMapping("api/v1/blog")
class BlogController(
        private val blogService: BlogService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm blog dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getBlogById(@PathVariable id: Long) : Blog =
            blogService.getBlogById(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllBlog() :List<Blog> =
            blogService.getAllBlog()


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addBlog(@RequestBody blogResponse: Blog) {
        blogService.addOrEditBlog(blogResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editBlog(@RequestBody blogResponse: Blog) {
        blogService.addOrEditBlog(blogResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteBlogById(@PathVariable id: Long) {
        blogService.deleteBlogById(id)
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteAllBlog() {
        blogService.deleteAllBlog()
    }
}