package com.auth.authproject.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContentController {

    @GetMapping("/api/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun hello():String{
        return "hello world"
    }
}