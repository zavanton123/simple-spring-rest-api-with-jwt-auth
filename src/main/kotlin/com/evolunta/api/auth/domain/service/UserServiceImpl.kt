package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.User
import com.evolunta.api.auth.data.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun fetchUserByUsername(username: String): User {
        val userOptional = userRepository.findByUsername(username)
        return if (userOptional.isPresent) {
            userOptional.get()
        } else {
            throw UsernameNotFoundException("The user with the username $username doe not exist")
        }
    }
}
