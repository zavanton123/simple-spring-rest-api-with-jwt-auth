package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.User

interface UserService {

    fun fetchUserByUsername(username: String): User
}
