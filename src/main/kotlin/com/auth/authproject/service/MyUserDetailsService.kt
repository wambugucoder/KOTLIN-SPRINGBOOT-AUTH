package com.auth.authproject.service

import com.auth.authproject.repository.UserRepo
import com.auth.authproject.security.MyUserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
 class MyUserDetailsService :UserDetailsService{
  lateinit var logger:Logger


    @Autowired
    lateinit var userRepo: UserRepo

    override fun loadUserByUsername(email: String) : UserDetails {
        if (userRepo.existsByEmail(email)){
            val newDetails= userRepo.findByEmail(email)
            return MyUserPrincipal(newDetails)
        }
       else{

            throw UsernameNotFoundException("The Email doesn't seem to exist")
       }






    }
}