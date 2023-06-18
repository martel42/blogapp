package ru.mks.rsoi.gateway.dto.response

import org.springframework.http.HttpStatusCode
import java.time.LocalDate
import java.time.LocalDateTime

data class StatsResponse(val dateRequest: LocalDateTime, val uri: String, val codeStatus: String)
