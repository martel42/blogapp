package ru.mks.rsoi.post

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PostApplication

fun main(args: Array<String>) {
	runApplication<PostApplication>(*args)
}
