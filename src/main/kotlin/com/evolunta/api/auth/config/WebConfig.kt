package com.evolunta.api.auth.config

import com.evolunta.api.util.EMPTY
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    // note: disable the default prefix ROLE_ for roles
    @Bean
    fun grantedAuthorityDefaults() : GrantedAuthorityDefaults {
        return GrantedAuthorityDefaults(EMPTY)
    }
}
