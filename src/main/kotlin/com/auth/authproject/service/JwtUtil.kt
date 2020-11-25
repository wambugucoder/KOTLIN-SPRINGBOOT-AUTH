package com.auth.authproject.service

import com.auth.authproject.models.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class JwtUtil {
    @Value("\${auth.security.key}")
     lateinit var securityKey:String

     fun  extractUsername(token:String):String{

         return extractClaim(token,Claims::getSubject)
     }
    fun extractExpiration(token:String): Date{
        return extractClaim(token,Claims::getExpiration)
    }
    fun <R> extractClaim(token: String, claimsResolver:Function<Claims,R>): R {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    fun extractAllClaims(token:String): Claims {
        return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).body
    }
    fun isTokenExpired(token:String):Boolean{
        return extractExpiration(token).before(Date())
    }
     fun generateToken(userDetails: User):String{
        var payload:Map<String,Any> = hashMapOf("username" to userDetails.username,
        "authorities" to userDetails.role, "email" to userDetails.email, "id" to userDetails.id)
         return createToken(payload,userDetails.username)

     }
    fun createToken(payload:Map<String,Any>,subject:String):String{
        return  Jwts.builder().setClaims(payload).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,securityKey).compact()
    }
    fun validateToken(token: String,userDetails:UserDetails):Boolean{
        val userName= extractUsername(token);
        return (userName == userDetails.username && !isTokenExpired(token))
    }

}