package ru.mks.rsoi.subs.service

import ru.mks.rsoi.subs.entity.Subs


interface SubsService {
    fun getSubsById(id: Long): Subs
    fun getAllSubs(): List<Subs>
    fun addOrEditSubs(role: Subs)
    fun deleteSubsById(id: Long)
    fun deleteAllSubs()
    fun deleteSubsPlus(uid: Long, bid: Long)
}