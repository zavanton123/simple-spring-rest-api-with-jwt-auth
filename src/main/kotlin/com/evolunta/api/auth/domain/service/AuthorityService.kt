package com.evolunta.api.auth.domain.service

import com.evolunta.api.auth.data.model.Authority

interface AuthorityService {

    fun findAuthorityByName(name: String): Authority
}
