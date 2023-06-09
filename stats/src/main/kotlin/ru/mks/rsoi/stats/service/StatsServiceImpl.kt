package ru.mks.rsoi.stats.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mks.rsoi.stats.entity.Stats
import ru.mks.rsoi.stats.repository.StatsRepository
import ru.mks.rsoi.stats.response.StatsResponse

@Service
class StatsServiceImpl(
        private val statsRepository: StatsRepository
): StatsService {
    @Transactional(readOnly = true)
    override fun getStatsById(id: Long): Stats =
            statsRepository.findById(id).orElseThrow {EntityNotFoundException ("Stats $id not found!")}

    @Transactional(readOnly = true)
    override fun getAllStats(): List<Stats> =
            statsRepository.findAll()

    @Transactional
    override fun addNewStats(stats: StatsResponse) {
        val newStats = Stats(
                id = -1,
                dateRequest = stats.dateRequest,
                uri = stats.uri,
                codeStatus = stats.codeStatus
        )
        statsRepository.save(newStats)
    }
}