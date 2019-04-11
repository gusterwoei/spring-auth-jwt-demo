package com.gusterwoei.jwtdemo.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class InvalidJwtAuthenticationException(var error: String) : RuntimeException() {
}