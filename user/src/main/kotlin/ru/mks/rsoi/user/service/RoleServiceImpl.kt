package ru.mks.rsoi.user.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.user.entity.Role
import ru.mks.rsoi.user.repository.RoleRepository

@Service
class RoleServiceImpl(
        val roleRepository: RoleRepository
): RoleService {
    @Transactional(readOnly = true)
    override fun getRoleById(id: Long): Role =
            roleRepository.findById(id).orElseThrow { EntityNotFoundException ("Role $id not found!") }

    @Transactional(readOnly = true)
    override fun getAllRole(): List<Role> =
            roleRepository.findAll()

    @Transactional
    override fun addOrEditRole(role: Role) {
        roleRepository.save(role)
    }

    @Transactional
    override fun deleteRoleById(id: Long) {
        roleRepository.deleteById(id)
    }

    @Transactional
    override fun deleteAllRoles() {
        roleRepository.deleteAll()
    }
}