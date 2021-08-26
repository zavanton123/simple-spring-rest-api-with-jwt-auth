package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.Authority
import com.evolunta.api.auth.data.repository.AuthorityRepository
import org.springframework.stereotype.Service

@Service
class AuthorityServiceImpl(
    private val authorityRepository: AuthorityRepository
) : AuthorityService {

    override fun findAuthorityByName(name: String): Authority {
        return authorityRepository.findByName(name)
    }
}