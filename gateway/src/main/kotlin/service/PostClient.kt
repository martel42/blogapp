package service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.PostResponse

@FeignClient(name = "post", url = "http://localhost:8084/api/v1/post")
interface PostClient {
    @GetMapping("/dummy")
     fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPostById(@PathVariable id: Long) : PostResponse

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
     fun getAllPost() :List<PostResponse>


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
     fun addPost(@RequestBody postResponse: PostResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editPost(@RequestBody postResponse: PostResponse)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePostById(@PathVariable id: Long)

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllPost()
}