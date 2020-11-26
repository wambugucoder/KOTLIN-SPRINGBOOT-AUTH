package com.auth.authproject.security

import com.auth.authproject.models.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class MyUserPrincipal(user:User) :UserDetails {
    private val newUser=user


    override fun getAuthorities(): List<SimpleGrantedAuthority> {
       return listOf(SimpleGrantedAuthority(newUser.role.toString()))
    }

    override fun getPassword(): String {

       return newUser.password
    }

    override fun getUsername(): String {
        return newUser.email
    }

    override fun isAccountNonExpired(): Boolean {
       return newUser.isNotExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return newUser.isNotLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return newUser.isNotExpired
    }

    override fun isEnabled(): Boolean {
        return newUser.isEnabled
    }
}