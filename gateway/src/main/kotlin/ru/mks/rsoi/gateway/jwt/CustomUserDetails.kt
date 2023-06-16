package ru.mks.rsoi.gateway.jwt

import ru.mks.rsoi.gateway.dto.response.UserResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import ru.mks.rsoi.gateway.enums.Role

@Component
class CustomUserDetails(): UserDetails {
    private var login: String? = null
    private var password: String? = null
    private var grantedAuthorities: Collection<GrantedAuthority?>? = null

    open fun fromUserResponseToCustomUserDetails(user: UserResponse): CustomUserDetails {
        val customUserDetails = CustomUserDetails()
        customUserDetails.login = user.login
        customUserDetails.password = user.password
        customUserDetails.grantedAuthorities = Role.valueOf(user.role.authority.uppercase())
                .authorities
        return customUserDetails
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return grantedAuthorities
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}