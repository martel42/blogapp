package ru.mks.rsoi.subs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mks.rsoi.subs.entity.UserSubs

@Repository
interface UserSubsRepository: JpaRepository<UserSubs, Long> {
}