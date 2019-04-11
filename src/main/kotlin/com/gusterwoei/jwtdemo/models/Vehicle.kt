package com.gusterwoei.jwtdemo.models

import java.io.Serializable
import javax.persistence.*

@Entity(name = "vehicles")
class Vehicle : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var name: String? = null
}