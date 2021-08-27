package com.evolunta.api.auth.exception

import com.evolunta.api.util.EMPTY

typealias SpringAccessDeniedException = org.springframework.security.access.AccessDeniedException

class PasswordNotConfirmedException(msg: String = EMPTY) : RuntimeException(msg)
