package ru.mks.rsoi.stats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@EnableKafka
@EnableFeignClients
class StatsApplication

fun main(args: Array<String>) {
	runApplication<StatsApplication>(*args)
}
