package com.evolunta.api.auth.config

import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.service.UserService
import com.evolunta.api.util.ADMIN_ROLE
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userService: UserService,
    @Value("\${ADMIN_USERNAME:admin}")
    private val username: String,
    @Value("\${ADMIN_PASSWORD:1234}")
    private val password: String,
) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        userService.createUser(
            CreateUserRequest(
                username = username,
                password = password,
                confirmedPassword = password,
                authorities = setOf(ADMIN_ROLE)
            )
        )
    }
}
