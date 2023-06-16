package ru.mks.rsoi.gateway.jwt

import ru.mks.rsoi.gateway.dto.response.UserResponse
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

import ru.mks.rsoi.gateway.service.UserClient

@Component
class CustomUserDetailService(
    val userClient: UserClient,
    val customUserDetails: CustomUserDetails
): UserDetailsService {
    override fun loadUserByUsername(username: String): CustomUserDetails {
        val userList: List<UserResponse> = userClient.getAllUser()
        val user = userList.find { it.login == username }
        println(user)
        return customUserDetails.fromUserResponseToCustomUserDetails(user!!)!!
    }
}