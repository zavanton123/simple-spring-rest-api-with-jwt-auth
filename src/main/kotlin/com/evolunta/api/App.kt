package com.evolunta.api

import com.evolunta.api.auth.exception.AuthExceptionHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(AuthExceptionHandler::class)
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
