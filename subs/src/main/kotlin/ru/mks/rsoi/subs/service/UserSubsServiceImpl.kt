package ru.mks.rsoi.subs.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.subs.entity.Subs
import ru.mks.rsoi.subs.entity.UserSubs
import ru.mks.rsoi.subs.repository.SubsRepository
import ru.mks.rsoi.subs.repository.UserSubsRepository

@Service
class UserSubsServiceImpl(
        val userSubsRepository: UserSubsRepository
): UserSubsService {
    @Transactional(readOnly = true)
    override fun getUserSubsById(id: Long): UserSubs =
            userSubsRepository.findById(id).orElseThrow { EntityNotFoundException ("Subs $id not found!") }

    @Transactional(readOnly = true)
    override fun getAllUserSubs(): List<UserSubs> =
            userSubsRepository.findAll()

    @Transactional
    override fun addOrEditUserSubs(subs: UserSubs) {
        userSubsRepository.save(subs)
    }

    @Transactional
    override fun deleteUserSubsById(id: Long) {
        userSubsRepository.deleteById(id)
    }

    @Transactional
    override fun deleteAllUserSubs() {
        userSubsRepository.deleteAll()
    }
}