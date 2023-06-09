package ru.mks.rsoi.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class BlogApplication

fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args)
}
