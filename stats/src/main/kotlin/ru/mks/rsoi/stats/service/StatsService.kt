package ru.mks.rsoi.stats.service

import ru.mks.rsoi.stats.entity.Stats
import ru.mks.rsoi.stats.response.StatsResponse

interface StatsService {
    fun getStatsById(id: Long): Stats
    fun getAllStats(): List<Stats>
    fun addNewStats(stats: StatsResponse)
}