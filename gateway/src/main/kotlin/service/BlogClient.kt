package service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.response.BlogResponse

@FeignClient(name = "blog", url = "http://localhost:8083/api/v1/blog")
interface BlogClient {
    @GetMapping("/dummy")
    fun dummyRequest() : ResponseEntity<String>

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getBlogById(@PathVariable id: Long) : BlogResponse

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllBlog() :List<BlogResponse>


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBlog(@RequestBody blogResponse: BlogResponse)

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editBlog(@RequestBody blogResponse: BlogResponse)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBlogById(@PathVariable id: Long)

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAllBlog()
}