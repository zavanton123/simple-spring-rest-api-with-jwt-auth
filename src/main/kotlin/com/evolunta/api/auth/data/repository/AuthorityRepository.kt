package com.evolunta.api.auth.data.repository

import com.evolunta.api.auth.data.model.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, Long> {

    fun findByName(name: String): Authority
}
