package com.gusterwoei.jwtdemo.controllers

import com.gusterwoei.jwtdemo.errors.VehicleNotFoundException
import com.gusterwoei.jwtdemo.models.Vehicle
import com.gusterwoei.jwtdemo.models.VehicleForm
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import com.gusterwoei.jwtdemo.repos.VehicleRepository
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author Guster
 * Created at 2019-04-09
 *
 **/

@RestController
@RequestMapping("/v1/vehicles")
class VehicleController(private val vehicles: VehicleRepository) {

    @GetMapping("")
    fun all(): ResponseEntity<*> {
        return ok(this.vehicles.findAll())
    }

    @PostMapping("")
    fun save(@RequestBody form: VehicleForm, request: HttpServletRequest): ResponseEntity<*> {
        val saved = this.vehicles.save(Vehicle().apply { this.name = form.name })
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/vehicles/{id}")
                        .buildAndExpand(saved.id)
                        .toUri())
                .build<Any>()
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseEntity<*> {
        return ok(this.vehicles.findById(id).orElseThrow<RuntimeException> { VehicleNotFoundException() })
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody form: VehicleForm): ResponseEntity<*> {
        val existed = this.vehicles.findById(id).orElseThrow<RuntimeException> { VehicleNotFoundException() }
        existed.name = form.name

        this.vehicles.save(existed)
        return noContent().build<Any>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        val existed = this.vehicles.findById(id).orElseThrow<RuntimeException> { VehicleNotFoundException() }
        this.vehicles.delete(existed)
        return noContent().build<Any>()
    }
}