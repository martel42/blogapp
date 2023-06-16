package ru.mks.rsoi.gateway.enums

import org.springframework.security.core.GrantedAuthority

enum class Permission: GrantedAuthority {
    USER {
        override fun getAuthority(): String {
            return "USER"
        }
    },
    ADMIN {
        override fun getAuthority(): String {
            return "ADMIN"
        }
    };
}