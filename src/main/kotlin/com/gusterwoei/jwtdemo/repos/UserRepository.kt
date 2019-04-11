package com.gusterwoei.jwtdemo.repos

import com.gusterwoei.jwtdemo.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): Optional<User>

}