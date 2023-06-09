package ru.mks.rsoi.auth.util.jwt

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.mks.rsoi.auth.dto.UserResponse
import ru.mks.rsoi.auth.service.UserClient

@Component
class CustomUserDetailService(
        val userClient: UserClient,
        val customUserDetails: CustomUserDetails
): UserDetailsService {
    override fun loadUserByUsername(username: String?): CustomUserDetails? {
        val userList: List<UserResponse> = userClient.getAllUser()
        val user = userList.find { it.login == username }
        return customUserDetails.fromUserResponseToCustomUserDetails(user!!)
    }
}