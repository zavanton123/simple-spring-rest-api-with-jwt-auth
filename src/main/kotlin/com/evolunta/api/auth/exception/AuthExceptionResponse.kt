package com.evolunta.api.auth.exception

data class AuthExceptionResponse(
    val message: String,
    val details: List<String>
)
