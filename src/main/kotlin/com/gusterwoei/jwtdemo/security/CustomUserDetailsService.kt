package com.gusterwoei.jwtdemo.security

import com.gusterwoei.jwtdemo.repos.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@Component
class CustomUserDetailsService(private val users: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return this.users.findByUsername(username).orElseThrow { UsernameNotFoundException("Username: $username not found") }
    }
}