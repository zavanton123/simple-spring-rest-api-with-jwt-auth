package com.evolunta.api.auth.config.jwt

import com.evolunta.api.auth.domain.service.CustomUserDetailService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtTokenFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailService: CustomUserDetailService,
) : OncePerRequestFilter() {

    companion object {
        private const val AUTHORIZATION_HEADER_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // get authorization header and validate it
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (header.isNullOrBlank() || !header.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        // get JWT token and validate it
        val token = header.split(" ")[1].trim()
        if (!jwtTokenUtil.validateToken(token)) {
            filterChain.doFilter(request, response)
            return
        }

        // get user identity and set it to spring SecurityContext
        val username = jwtTokenUtil.extractUsername(token)
        val userDetails = userDetailService.loadUserByUsername(username)
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
