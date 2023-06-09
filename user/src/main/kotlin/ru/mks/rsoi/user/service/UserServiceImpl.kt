package ru.mks.rsoi.user.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.user.entity.User
import ru.mks.rsoi.user.repository.RoleRepository
import ru.mks.rsoi.user.repository.UserRepository
import ru.mks.rsoi.user.response.UserResponse

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
): UserService {
    @Transactional(readOnly = true)
    override fun getUserById(id: Long): User =
            userRepository.findById(id).orElseThrow {EntityNotFoundException ("User $id not found!")}

    @Transactional(readOnly = true)
    override fun getAllUser(): List<User> =
            userRepository.findAll()

    @Transactional
    override fun addOrEditUser(user: UserResponse) {
        val newUser = User (-1, user.UID, user.login, user.password, user.gender,
                user.phoneNumber, user.birthDate, user.registerDate, user.role.id)
        userRepository.save(newUser)
    }
}