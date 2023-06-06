package ru.mks.rsoi.gateway.util

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Component
@Order(1)
class RequestFilter : jakarta.servlet.Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        chain!!.doFilter(req, res)
        println(req.requestURI)
        println(res.status)
        println(res.outputStream)
    }
}