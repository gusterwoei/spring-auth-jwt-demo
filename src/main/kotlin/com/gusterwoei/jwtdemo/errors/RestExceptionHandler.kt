package com.gusterwoei.jwtdemo.errors

import com.gusterwoei.jwtdemo.errors.VehicleNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity.notFound
import org.springframework.web.context.request.WebRequest
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice



/**
 *
 * @author Guster
 * Created at 2019-04-09
 *
 **/

@RestControllerAdvice
//@Slf4j
class RestExceptionHandler {

    internal var logger = LoggerFactory.getLogger("ABC")

    @ExceptionHandler(value = [ VehicleNotFoundException::class ])
    fun vehicleNotFound(ex: VehicleNotFoundException, request: WebRequest): ResponseEntity<*> {
        logger.debug("handling vehicle not found exception...")
        return notFound().build<Any>()
    }

    @ExceptionHandler(value = [ InvalidJwtAuthenticationException::class ])
    fun invalidAccessToken(ex: VehicleNotFoundException, request: WebRequest): ResponseEntity<Any> {
        logger.debug("handling invalid access token exception...")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
}