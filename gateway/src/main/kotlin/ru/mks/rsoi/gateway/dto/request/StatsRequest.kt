package ru.mks.rsoi.gateway.dto.request

import org.springframework.http.HttpStatusCode
import java.time.LocalDate
import java.time.LocalDateTime

data class StatsRequest(val dateRequest: LocalDateTime, val uri: String, val codeStatus: HttpStatusCode)
