package com.auth.authproject.repository

import com.auth.authproject.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo :JpaRepository<User,Long>{
    fun existsByEmail(email:String):Boolean
    fun findByEmail(email:String):User

}