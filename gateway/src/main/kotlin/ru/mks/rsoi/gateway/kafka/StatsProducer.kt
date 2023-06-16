package ru.mks.rsoi.gateway.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.mks.rsoi.gateway.dto.request.StatsRequest

@Service
class StatsProducer(val simpleProducer: KafkaTemplate<String, String>) {
    fun sendStats(statsRequest: StatsRequest) {
        val stats = statsRequest.uri + " " + statsRequest.codeStatus.value()
        simpleProducer.send("sas", stats)
    }
}