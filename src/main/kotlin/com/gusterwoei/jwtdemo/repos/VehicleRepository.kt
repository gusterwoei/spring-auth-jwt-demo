package com.gusterwoei.jwtdemo.repos

import com.gusterwoei.jwtdemo.models.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 *
 * @author Guster
 * Created at 2019-04-09
 *
 **/

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long>