package com.evolunta.api.auth.config

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint

@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class SecurityConfig(
    private val passwordEncoder: PasswordEncoder,
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {


    init {
        // inherit security context in async function calls
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
    }


    override fun configure(http: HttpSecurity) {
        // enable cors()
        http.cors()
            .and()

            // disable csrf
            .csrf().disable()

            // set session management to stateless
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            // setup unauthorized requests exception handling
            .exceptionHandling()
            .authenticationEntryPoint(ExceptionEntryPoint())
            .and()

            // setup url authorization access
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()

            .httpBasic()

        // Add JWT token filter
        // .addFilterBefore()
    }

    class ExceptionEntryPoint : AuthenticationEntryPoint {

        private val log = LoggerFactory.getLogger(ExceptionEntryPoint::class.java)

        override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
        ) {
            log.info("zavanton2 - commence")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
        }
    }
}
