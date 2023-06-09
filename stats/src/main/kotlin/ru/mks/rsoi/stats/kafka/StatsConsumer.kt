package ru.mks.rsoi.stats.kafka

import org.springframework.http.HttpStatusCode
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.mks.rsoi.stats.response.StatsResponse
import ru.mks.rsoi.stats.service.StatsService
import java.time.LocalDateTime

@Service
class StatsConsumer(
        val statsService: StatsService
) {
    @KafkaListener(id = "simple-consumer", topics = ["sas"])
    fun consumeMessage(message: String) {
        val data = message.split(" ")
        val statsResponse = StatsResponse(LocalDateTime.now(), data[0],
                HttpStatusCode.valueOf(data[1].toInt()) )
        statsService.addNewStats(statsResponse)
    }
}