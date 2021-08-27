package com.evolunta.api.auth.controller

import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.dto.CreateUserResponse
import com.evolunta.api.auth.domain.service.UserService
import com.evolunta.api.util.ADMIN_AUTHORITY
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestAuthController(
    private val userService: UserService
) {

    @GetMapping("/demo")
    fun index(): String {
        return "index"
    }

    @GetMapping("/users")
    @Secured(ADMIN_AUTHORITY)
    fun user(): String = "user page"


    @PostMapping("/users/create")
    @Secured(ADMIN_AUTHORITY)
    fun createUser(
        @RequestBody request: CreateUserRequest
    ): CreateUserResponse {
        return userService.createUser(request)
    }

    @GetMapping("/admin")
    @Secured(ADMIN_AUTHORITY)
    fun admin(): String = "admin page"
}
