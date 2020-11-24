package com.auth.authproject.requests

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


class SignUpRequest (

        @field:NotEmpty(message = "UserName cannot be Empty")
        @field:Size(min = 6,max = 12,message = "UserName must Contain 6-12 Characters")
        val username:String,

        @field:Email(message = "Invalid Email Detected")
        @field:NotEmpty(message = "Email cannot be Empty")
        val email:String,

        @field:NotEmpty(message = "Password cannot be Empty")
        @field:Size(min = 6,message = "UserName must Contain 6 or More Characters")
        val password:String
        )
{
    private constructor():this(username = "",email = "",password = "")
}