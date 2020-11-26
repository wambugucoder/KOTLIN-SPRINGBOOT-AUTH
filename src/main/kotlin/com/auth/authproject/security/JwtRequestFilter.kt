package com.auth.authproject.security

import com.auth.authproject.service.JwtUtil
import com.auth.authproject.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var userDetailsService: MyUserDetailsService

    @Autowired
    lateinit var jwtUtil: JwtUtil

     var jwt: String? = null
     var email:String?=null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val  authHeader= request.getHeader("Authorization")

        if (authHeader!= null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7)
            email = jwtUtil.extractEmail(jwt!!)
        }

            if(email !=null && SecurityContextHolder.getContext().authentication ==null){
                val confirmDetails =userDetailsService.loadUserByUsername(email!!)
                if(jwtUtil.validateToken(jwt!!,confirmDetails)){
                    //NORMAL FLOW OF OPERATION
                    val usernamePasswordAuthenticationToken =UsernamePasswordAuthenticationToken(confirmDetails,null,confirmDetails.authorities)
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }
             chain.doFilter(request,response)
        }
}