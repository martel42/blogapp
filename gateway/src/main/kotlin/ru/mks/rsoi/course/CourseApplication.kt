package ru.mks.rsoi.course

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CourseApplication

fun main(args: Array<String>) {
	runApplication<CourseApplication>(*args)
}
