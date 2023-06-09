package ru.mks.rsoi.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mks.rsoi.user.entity.Role

@Repository
interface RoleRepository: JpaRepository<Role, Long>