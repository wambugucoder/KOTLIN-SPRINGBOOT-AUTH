package com.auth.authproject.security


import com.auth.authproject.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApiSecurity : WebSecurityConfigurerAdapter() {

    @Autowired
     lateinit var userDetailsService: MyUserDetailsService




    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder())

    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.csrf().disable()
                    http.authorizeRequests()
                            .antMatchers("/api/auth").permitAll()
                            .antMatchers("/api/hello").authenticated()
                            .and()
                            .formLogin().permitAll()




        }

    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun authenticationProvider():AuthenticationProvider{
        val provider= DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService)
        provider.setPasswordEncoder(passwordEncoder())

        return provider
    }

}


