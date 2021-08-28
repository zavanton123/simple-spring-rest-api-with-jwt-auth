package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.User
import com.evolunta.api.auth.data.repository.UserRepository
import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.dto.CreateUserResponse
import com.evolunta.api.auth.domain.mapper.UserMapper
import com.evolunta.api.auth.exception.PasswordNotConfirmedException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
) : UserService {

    override fun fetchUserByUsername(username: String): User {
        val userOptional = userRepository.findByUsername(username)
        return if (userOptional.isPresent) {
            userOptional.get()
        } else {
            throw UsernameNotFoundException("The user with the username $username doe not exist")
        }
    }

    override fun createUser(request: CreateUserRequest): CreateUserResponse {
        if (request.password != request.confirmedPassword) {
            throw PasswordNotConfirmedException("The password and confirmed password must match")
        }

        val user = userMapper.mapToEntity(request)
        user.password = passwordEncoder.encode(user.password)
        val savedUser = userRepository.save(user)
        return userMapper.mapToCreateUserResponse(savedUser)
    }
}
