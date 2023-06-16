package ru.mks.rsoi.gateway.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import ru.mks.rsoi.gateway.dto.request.StatsRequest
import ru.mks.rsoi.gateway.dto.response.StatsResponse

@FeignClient(name = "stats", url = "http://localhost:8081/api/v1/stats")
interface StatsClient {
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStatsById(@PathVariable id: Long) : StatsResponse
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllStats() :List<StatsResponse>
}