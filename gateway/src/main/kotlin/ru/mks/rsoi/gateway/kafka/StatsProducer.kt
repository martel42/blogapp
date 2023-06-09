package ru.mks.rsoi.gateway.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.mks.rsoi.gateway.response.StatsResponse

@Service
class StatsProducer(val simpleProducer: KafkaTemplate<String, String>) {
    fun sendStats(statsResponse: StatsResponse) {
        val stats = statsResponse.uri + " " + statsResponse.codeStatus.value()
        simpleProducer.send("sas", stats)
    }
}