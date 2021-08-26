package com.evolunta.api.auth.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

    @GetMapping("/demo")
    fun index(): String = "index"
}
