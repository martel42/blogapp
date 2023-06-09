package ru.mks.rsoi.gateway.util

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import ru.mks.rsoi.gateway.kafka.StatsProducer
import ru.mks.rsoi.gateway.response.StatsResponse
import java.time.LocalDate


@Component
@Order(1)
class RequestFilter (
        val statsProducer: StatsProducer
): jakarta.servlet.Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        chain!!.doFilter(req, res)
        println(req.requestURI)
        println(res.status)
        HttpStatusCode.valueOf(200)
        val statsResponse = StatsResponse(LocalDate.now(), req.requestURI, HttpStatusCode.valueOf(res.status))
        statsProducer.sendStats(statsResponse)
    }
}