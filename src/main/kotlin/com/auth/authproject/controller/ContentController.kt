package com.auth.authproject.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContentController {

    @GetMapping("/api/hello")
    fun hello():String{
        return "hello world"
    }
}