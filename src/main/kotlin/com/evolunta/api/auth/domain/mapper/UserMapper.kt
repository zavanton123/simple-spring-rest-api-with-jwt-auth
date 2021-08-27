package com.evolunta.api.auth.domain.mapper

import com.evolunta.api.auth.data.model.Authority
import com.evolunta.api.auth.data.model.User
import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.dto.CreateUserResponse
import org.springframework.stereotype.Component

interface UserMapper {

    fun mapToEntity(request: CreateUserRequest): User
    fun mapToResponse(user: User): CreateUserResponse
}

@Component
class UserMapperImpl : UserMapper {

    override fun mapToEntity(request: CreateUserRequest): User {
        val user = User(
            id = request.id,
            username = request.username,
            password = request.password
        )
        user.authorities
            .addAll(request.authorities.map { name ->
                Authority(
                    id = 0L,
                    name = name
                )
            })
        return user
    }

    override fun mapToResponse(user: User): CreateUserResponse {
        return CreateUserResponse(
            id = user.id,
            username = user.username,
            authorities = user.authorities.map { it.name }
        )
    }
}
