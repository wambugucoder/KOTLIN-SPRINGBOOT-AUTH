package com.auth.authproject.service

import com.auth.authproject.models.User
import com.auth.authproject.repository.UserRepo
import com.auth.authproject.requests.SignUpRequest
import com.auth.authproject.response.SignUpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {
    
    @Autowired
    lateinit var userRepo: UserRepo


    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun registerUser(body:SignUpRequest): ResponseEntity<SignUpResponse>{
        return if (userRepo.existsByEmail(body.email)){

            ResponseEntity.badRequest().body(SignUpResponse(HttpStatus.BAD_REQUEST,"Email Already Exists"))

        }
        else{
            val newPassword =bCryptPasswordEncoder.encode(body.password)
            val newUser=User(username=body.username,email = body.email,password = newPassword)
            userRepo.save(newUser)
            ResponseEntity.ok(SignUpResponse(HttpStatus.OK,"User has been saved Successfully"))

        }
    }
    fun fetchUserDetail(email:String): User {
        return userRepo.findByEmail(email)
    }




}
