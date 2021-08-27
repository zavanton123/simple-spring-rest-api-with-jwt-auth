package com.evolunta.api.auth.domain.dto

data class CreateUserResponse(
    val id: Long,
    val username: String,
    val authorities: List<String>
)
