package com.auth.authproject.response

import org.springframework.http.HttpStatus

class LoginResponse(
        var httpStatus: HttpStatus,
        var message:String,
        var token:String,
){
    public constructor(loginResponse: LoginResponse):this(httpStatus =loginResponse.httpStatus ,message = loginResponse.message,token =loginResponse.token){
        httpStatus=loginResponse.httpStatus
        message=loginResponse.message
        token=loginResponse.token


    }
}