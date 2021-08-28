package com.evolunta.api.auth.controller

import com.evolunta.api.auth.config.jwt.JwtTokenUtil
import com.evolunta.api.auth.domain.dto.CreateUserRequest
import com.evolunta.api.auth.domain.dto.CreateUserResponse
import com.evolunta.api.auth.domain.dto.LoginRequest
import com.evolunta.api.auth.domain.dto.LoginResponse
import com.evolunta.api.auth.domain.mapper.UserMapper
import com.evolunta.api.auth.domain.service.UserService
import com.evolunta.api.util.ADMIN_AUTHORITY
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userMapper: UserMapper,
) {

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        try {
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.username,
                    loginRequest.password
                )
            )
            val userDetails = authentication.principal as UserDetails
            val user = userService.fetchUserByUsername(userDetails.username)

            return ResponseEntity.status(HttpStatus.OK)
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateAccessToken(user)
                )
                .body(userMapper.mapToLoginResponse(user))
        } catch (exception: BadCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @GetMapping("/demo")
    fun index(): String {
        return "index"
    }

    @GetMapping("/users")
    @Secured(ADMIN_AUTHORITY)
    fun user(): String = "user page"


    @PostMapping("/users/create")
    @Secured(ADMIN_AUTHORITY)
    fun createUser(
        @RequestBody request: CreateUserRequest
    ): CreateUserResponse {
        return userService.createUser(request)
    }

    @GetMapping("/admin")
    @Secured(ADMIN_AUTHORITY)
    fun admin(): String = "admin page"
}
