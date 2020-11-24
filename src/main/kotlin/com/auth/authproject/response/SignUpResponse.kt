package com.auth.authproject.response

import org.springframework.http.HttpStatus



class SignUpResponse(
        val  httpStatus: HttpStatus,
        val message:String

) {
    private constructor(httpStatus: HttpStatus):this(httpStatus = httpStatus,message = "")
}