package ru.mks.rsoi.stats.response

import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

data class StatsResponse(val dateRequest: LocalDateTime, val uri: String, val codeStatus: HttpStatusCode)
