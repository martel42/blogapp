package ru.mks.rsoi.stats.response

import org.springframework.http.HttpStatusCode
import java.time.LocalDate

data class StatResponse(val localDate: LocalDate, val uri: String, val codeStatus: HttpStatusCode)
