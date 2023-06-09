package ru.mks.rsoi.post.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.post.entity.Post
import ru.mks.rsoi.post.service.PostService

@RestController
@CrossOrigin("http://localhost:8084/api/v1/post")
@RequestMapping("api/v1/post")
class PostController(
        private val postService: PostService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm post dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getPostById(@PathVariable id: Long) : Post =
            postService.getPostById(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllPost() :List<Post> =
            postService.getAllPost()


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addPost(@RequestBody postResponse: Post) {
        postService.addOrEditPost(postResponse)
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private fun editPost(@RequestBody postResponse: Post) {
        postService.addOrEditPost(postResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deletePostById(@PathVariable id: Long) {
        postService.deletePostById(id)
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteAllPost() {
        postService.deleteAllPost()
    }
}