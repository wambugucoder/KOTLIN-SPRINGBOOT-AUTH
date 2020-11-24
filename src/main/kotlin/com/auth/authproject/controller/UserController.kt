package com.auth.authproject.controller

import com.auth.authproject.requests.SignUpRequest
import com.auth.authproject.response.SignUpResponse
import com.auth.authproject.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class UserController {

    @Autowired
    lateinit var userService:UserService

    @PostMapping("/register",consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun registerUser(@RequestBody @Valid body:SignUpRequest,finalR:BindingResult): ResponseEntity<SignUpResponse> {
        return if (finalR.hasErrors()){
            ResponseEntity.badRequest().body(SignUpResponse(HttpStatus.BAD_REQUEST,"Invalid Credentials"))
        }
        else{
            userService.registerUser(body)
        }

    }
}