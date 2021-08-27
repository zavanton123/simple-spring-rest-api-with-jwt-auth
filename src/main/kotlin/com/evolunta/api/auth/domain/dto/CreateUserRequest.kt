package com.evolunta.api.auth.domain.dto

data class CreateUserRequest(
    val id: Long = 0L,
    val username: String,
    val password: String,
    val confirmedPassword: String,
    val authorities: Set<String> = mutableSetOf()
)
