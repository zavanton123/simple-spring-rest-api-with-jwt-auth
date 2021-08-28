package com.evolunta.api.auth.domain.dto

data class LoginRequest(
    val username: String,
    val password: String
)
