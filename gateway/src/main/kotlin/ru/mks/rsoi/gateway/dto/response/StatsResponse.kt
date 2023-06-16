package ru.mks.rsoi.gateway.dto.response

import org.springframework.http.HttpStatusCode
import java.time.LocalDate

data class StatsResponse(val localDate: LocalDate, val uri: String, val codeStatus: HttpStatusCode)
