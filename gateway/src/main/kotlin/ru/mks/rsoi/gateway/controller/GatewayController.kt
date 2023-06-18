package ru.mks.rsoi.gateway.controller

import ru.mks.rsoi.gateway.dto.request.AuthRequest
import ru.mks.rsoi.gateway.dto.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.gateway.dto.request.StatsRequest
import ru.mks.rsoi.gateway.dto.response.*
import ru.mks.rsoi.gateway.service.*


@RestController
@CrossOrigin("http://localhost:8080/api/v1/")
@RequestMapping("api/v1/")
class GatewayController(
        val userClient: UserClient,
        val userSubsClient: UserSubsClient,
        val subsClient: SubsClient,
        val roleClient: RoleClient,
        val postClient: PostClient,
        val blogClient: BlogClient,
        val authClient: AuthClient,
        val statsClient: StatsClient
) {

    @GetMapping("dummy")
    @ResponseStatus(HttpStatus.OK)
    private fun dummyResponse() : String {
        return "ResponseEntity<String>(I'm gateway dummy!, HttpStatus.OK)"
    }

    @GetMapping("post/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPostById(@PathVariable id: Long) : PostResponse = postClient.getPostById(id)
    @GetMapping("post/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllPost() :List<PostResponse> = postClient.getAllPost()

    @PostMapping("post/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addPost(@RequestBody userResponse: PostResponse) {
        postClient.addPost(userResponse)
    }

    @PutMapping("post/")
    @ResponseStatus(HttpStatus.OK)
    fun editPost(@RequestBody userResponse: PostResponse) {
        postClient.editPost(userResponse)
    }

    @DeleteMapping("post/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePostById(@PathVariable id: Long) {
        postClient.deletePostById(id)
    }

    @GetMapping("blog/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getBlogById(@PathVariable id: Long) : BlogResponse = blogClient.getBlogById(id)

    @GetMapping("blog/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllBlog() :List<BlogResponse> = blogClient.getAllBlog()

    @PostMapping("blog/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBlog(@RequestBody userResponse: BlogResponse) {
        blogClient.addBlog(userResponse)
    }

    @PutMapping("blog/")
    @ResponseStatus(HttpStatus.CREATED)
    fun editBlog(@RequestBody userResponse: BlogResponse) {
        blogClient.editBlog(userResponse)
    }

    @GetMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getRoleById(@PathVariable id: Long) : RoleResponse = roleClient.getRoleById(id)
    @GetMapping("role/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllRole() :List<RoleResponse> = roleClient.getAllRole()

    @GetMapping("subs/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getSubsById(@PathVariable id: Long) : SubsResponse = subsClient.getSubsById(id)
    @GetMapping("subs/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllSubs() :List<SubsResponse> = subsClient.getAllSubs()

    @PostMapping("subs/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addSubs(@RequestBody userResponse: SubsResponse) {
        subsClient.addSubs(userResponse)
    }

    @GetMapping("userSubs/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserSubsById(@PathVariable id: Long) : UserSubsResponse = userSubsClient.getUserSubsById(id)
    @GetMapping("userSubs/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllUserSubs() :List<UserSubsResponse> = userSubsClient.getAllUserSubs()

    @PostMapping("userSubs/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUserSubs(@RequestBody userResponse: UserSubsResponse) {
        userSubsClient.addUserSubs(userResponse)
    }


    @DeleteMapping("subs/plus/{uid}/{bid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSubsPlus(@PathVariable uid: Long, @PathVariable bid: Long) {
        subsClient.deleteSubsPlus(uid,bid)
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(@PathVariable id: Long) : UserResponse = userClient.getUserById(id)
    @GetMapping("user/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllUser() :List<UserResponse> = userClient.getAllUser()

    @PostMapping("user/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody userResponse: UserResponse) {
        userClient.addUser(userResponse)
    }

    @PostMapping("login/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun auth(@RequestBody request: AuthRequest): AuthResponse {
        return authClient.login(request)
    }

    @GetMapping("stats/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStatsById(@PathVariable id: Long) : StatsResponse {
        return statsClient.getStatsById(id)
    }
    @GetMapping("stats/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllStats() :List<StatsResponse>{
        return statsClient.getAllStats()
    }

}
