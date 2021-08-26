package com.evolunta.api.auth.controller

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    private val log = LoggerFactory.getLogger(AuthController::class.java)

    @GetMapping("/demo")
    fun index(): String {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        log.info("zavanton - username: ${user.username}")
        log.info("zavanton - password ${user.password}")
        user.authorities.forEach {
            log.info("zavanton - authority: ${it.authority}")
        }

        return "index"
    }

    @GetMapping("/user")
    fun user(): String = "user page"

    @GetMapping("/admin")
    fun admin(): String = "admin page"
}
