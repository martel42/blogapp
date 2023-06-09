package ru.mks.rsoi.stats.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mks.rsoi.stats.entity.Stats

@Repository
interface StatsRepository: JpaRepository<Stats, Long>