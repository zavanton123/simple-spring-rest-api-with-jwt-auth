package com.evolunta.api.auth.exception

import com.evolunta.api.util.EMPTY
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AuthExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        const val VALIDATION_ERROR = "Validation Error"
    }

    @ExceptionHandler(PasswordNotConfirmedException::class)
    fun handlePasswordException(
        exception: PasswordNotConfirmedException
    ): ResponseEntity<AuthExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                AuthExceptionResponse(
                    message = VALIDATION_ERROR,
                    details = listOf(exception.message ?: EMPTY)
                )
            )
    }
}
