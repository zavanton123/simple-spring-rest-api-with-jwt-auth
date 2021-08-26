package com.evolunta.api.auth.domain

import com.evolunta.api.util.EMPTY
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    var name: String = EMPTY
)
