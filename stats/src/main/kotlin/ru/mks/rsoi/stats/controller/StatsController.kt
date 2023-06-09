package ru.mks.rsoi.stats.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mks.rsoi.stats.response.StatsResponse
import ru.mks.rsoi.stats.service.StatsService

@RestController
@CrossOrigin("http://localhost:8081/api/v1/stats")
@RequestMapping("api/v1/stats")
class StatsController(
        private val statsService: StatsService
) {
    @GetMapping("/dummy")
    private fun dummyRequest() : ResponseEntity<String> {
        return ResponseEntity<String>("I'm stats dummy!", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private fun getStatsById(@PathVariable id: Long) : StatsResponse {
        val reqStats = statsService.getStatsById(id)
        return StatsResponse(reqStats.dateRequest, reqStats.uri, reqStats.codeStatus)
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllStats() :List<StatsResponse> {
        return statsService.getAllStats().map { StatsResponse(it.dateRequest, it.uri, it.codeStatus) }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private fun addStats(@RequestBody statsResponse: StatsResponse) {
        statsService.addNewStats(statsResponse)
    }
}
