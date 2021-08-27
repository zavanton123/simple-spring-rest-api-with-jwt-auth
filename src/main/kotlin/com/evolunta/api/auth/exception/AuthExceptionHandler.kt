package com.evolunta.api.auth.exception

import com.evolunta.api.util.EMPTY
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AuthExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        const val VALIDATION_EXCEPTION = "Validation Exception"
        const val ACCESS_DENIED_EXCEPTION = "Access Denied Exception"
    }

    // todo zavanton - uncomment
//    @ExceptionHandler(PasswordNotConfirmedException::class)
//    fun handlePasswordException(
//        exception: PasswordNotConfirmedException
//    ): ResponseEntity<AuthExceptionResponse> {
//        return createAuthExceptionResponse(
//            exception,
//            VALIDATION_EXCEPTION,
//            HttpStatus.BAD_REQUEST
//        )
//    }
//
//    @ExceptionHandler(SpringAccessDeniedException::class)
//    fun handleAccessDeniedException(
//        exception: SpringAccessDeniedException
//    ): ResponseEntity<AuthExceptionResponse> {
//        return createAuthExceptionResponse(
//            exception,
//            ACCESS_DENIED_EXCEPTION,
//            HttpStatus.FORBIDDEN
//        )
//    }
//
    private fun createAuthExceptionResponse(
        exception: Exception,
        message: String,
        httpStatus: HttpStatus
    ): ResponseEntity<AuthExceptionResponse> {
        return ResponseEntity.status(httpStatus)
            .body(
                AuthExceptionResponse(
                    message = message,
                    details = listOf(exception.message ?: EMPTY)
                )
            )
    }
}
