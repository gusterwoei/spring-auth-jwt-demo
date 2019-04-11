package com.gusterwoei.jwtdemo

import com.gusterwoei.jwtdemo.models.User
import com.gusterwoei.jwtdemo.models.Vehicle
import com.gusterwoei.jwtdemo.repos.VehicleRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*
import org.springframework.security.crypto.password.PasswordEncoder
import com.gusterwoei.jwtdemo.repos.UserRepository

/**
 *
 * @author Guster
 * Created at 2019-04-09
 *
 **/

@Component
class DataInitializer : CommandLineRunner {

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    internal var logger = LoggerFactory.getLogger("ABC")

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        //logger.debug("initializing vehicleRepository data...")
        //Arrays.asList("moto", "car").forEach {
        //    v -> this.vehicleRepository.saveAndFlush(Vehicle().apply { this.name = v })
        //}
        //
        //logger.debug("printing all vehicles...")
        //this.vehicleRepository.findAll().forEach { v ->
        //    logger.debug(" Vehicle :$v")
        //}
        //
        //this.userRepository.save(User().apply {
        //    this.username = "user"
        //    this.password = passwordEncoder.encode("password")
        //    this.roles = arrayListOf("ROLE_USER")
        //})
        //
        //this.userRepository.save(User().apply {
        //    this.username = "admin"
        //    this.password = passwordEncoder.encode("password")
        //    this.roles = arrayListOf("ROLE_USER", "ROLE_ADMIN")
        //})
        //
        //logger.debug("printing all users...")
        //this.userRepository.findAll().forEach { v -> logger.debug(" User :$v") }
    }
}