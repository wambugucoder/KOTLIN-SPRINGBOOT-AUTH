package com.auth.authproject.controller

import com.auth.authproject.requests.LoginRequest
import com.auth.authproject.requests.SignUpRequest
import com.auth.authproject.response.LoginResponse
import com.auth.authproject.response.SignUpResponse
import com.auth.authproject.service.JwtUtil
import com.auth.authproject.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class UserController {

    @Autowired
    lateinit var userService:UserService
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtUtil: JwtUtil

    @PostMapping("/register",consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun registerUser(@RequestBody @Valid body:SignUpRequest,finalR:BindingResult): ResponseEntity<SignUpResponse> {
        return if (finalR.hasErrors()){
            ResponseEntity.badRequest().body(SignUpResponse(HttpStatus.BAD_REQUEST,"Empty Credentials"))
        }
        else{
            userService.registerUser(body)
        }

    }
    @PostMapping("/login",consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun loginUser(@RequestBody @Valid body:LoginRequest,finalR: BindingResult): ResponseEntity<LoginResponse> {
      if (finalR.hasErrors()){
          return ResponseEntity.badRequest().body(LoginResponse(httpStatus = HttpStatus.BAD_REQUEST,message = "Bad Credentials",token = "Empty"))
      }
        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(body.email,body.password)
            )
        }
        catch (e:BadCredentialsException){
            return ResponseEntity.badRequest().body(LoginResponse(httpStatus = HttpStatus.FORBIDDEN,message = "Invalid UserName or Password",token = "Empty"))

        }
       val detailsOfThisUser=userService.fetchUserDetail(body.email)
        val jwttoken= jwtUtil.generateToken(detailsOfThisUser)
        return ResponseEntity.ok(LoginResponse(httpStatus = HttpStatus.OK,message = "SuccessFully Authenticated User",token = jwttoken))
    }

}