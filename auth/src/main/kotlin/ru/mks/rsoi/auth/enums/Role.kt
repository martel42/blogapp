package ru.mks.rsoi.auth.enums

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors


enum class Role(private val permissions: Set<Permission>) {
    MODERATOR(mutableSetOf<Permission>(Permission.USER)),
    ADMIN(mutableSetOf<Permission>(Permission.USER, Permission.ADMIN));

    val authorities: Set<SimpleGrantedAuthority>
        get() = permissions.stream()
                .map<SimpleGrantedAuthority> { p: Permission -> SimpleGrantedAuthority(p.name) }
                .collect(Collectors.toSet<SimpleGrantedAuthority>())
}