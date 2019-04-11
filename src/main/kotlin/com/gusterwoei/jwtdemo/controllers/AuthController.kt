package com.gusterwoei.jwtdemo.controllers

import com.gusterwoei.jwtdemo.models.AuthenticationRequest
import org.springframework.security.authentication.BadCredentialsException
import java.util.HashMap
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import com.gusterwoei.jwtdemo.repos.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import com.gusterwoei.jwtdemo.security.JwtTokenProvider
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    internal lateinit var authenticationManager: AuthenticationManager

    @Autowired
    internal lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    internal lateinit var userRepository: UserRepository

    @PostMapping("/signin")
    fun signin(@RequestBody data: AuthenticationRequest): ResponseEntity<*> {
        try {
            val username = data.username
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, data.password))
            val roles = this.userRepository.findByUsername(username).orElseThrow {
                UsernameNotFoundException("Username " + username + "not found")
            }.roles

            val token = jwtTokenProvider.createToken(username, roles)

            // response
            val model = HashMap<Any, Any>()
            model["accessToken"] = token
            model["tokenType"] = "bearer"
            model["expiresIn"] = 5000
            model["refreshToken"] = "abc"
            return ok(model)

        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username/password supplied")
        }
    }

}