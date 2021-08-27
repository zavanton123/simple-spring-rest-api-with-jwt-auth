package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.User
import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.dto.CreateUserResponse

interface UserService {

    fun fetchUserByUsername(username: String): User

    fun createUser(request: CreateUserRequest): CreateUserResponse
}
