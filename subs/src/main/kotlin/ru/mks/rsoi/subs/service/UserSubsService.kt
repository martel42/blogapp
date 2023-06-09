package ru.mks.rsoi.subs.service

import ru.mks.rsoi.subs.entity.UserSubs

interface UserSubsService {
    fun getUserSubsById(id: Long): UserSubs
    fun getAllUserSubs(): List<UserSubs>
    fun addOrEditUserSubs(role: UserSubs)
    fun deleteUserSubsById(id: Long)
    fun deleteAllUserSubs()
}