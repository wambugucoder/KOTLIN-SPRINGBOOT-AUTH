package com.auth.authproject.requests

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class LoginRequest(
        @field:NotEmpty(message = "Email Cannot Be Empty")
        @field:Email(message = "Invalid Email")
        val email:String,

        @field:NotEmpty(message = "Password Cannot Be Empty")
        val  password:String
) {
    private constructor():this("","")
}