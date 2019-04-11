package com.gusterwoei.jwtdemo.controllers

import org.springframework.security.core.GrantedAuthority
import java.util.HashMap
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors.toList


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@RestController
class UserinfoController {

    @GetMapping("/me")
    fun currentUser(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<*> {
        val model = HashMap<Any, Any>()
        model["username"] = userDetails.username
        model["roles"] = userDetails.authorities
                .stream()
                .map { a -> (a as GrantedAuthority).authority }
                .collect(toList())
        return ok(model)
    }
}