package com.gusterwoei.jwtdemo.models

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority
import java.util.ArrayList
import org.springframework.security.core.userdetails.UserDetails
import java.util.function.Function
import java.util.stream.Collectors.toList
import javax.persistence.*
import javax.validation.constraints.NotEmpty


/**
 *
 * @author Guster
 * Created at 2019-04-10
 *
 **/

@Entity
@Table(name = "users")
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotEmpty
    internal var username: String = ""

    @NotEmpty
    internal var password: String = ""

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: List<String> = arrayListOf()

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return this.roles.stream().map<SimpleGrantedAuthority> {
            SimpleGrantedAuthority(it)
        }.collect(toList())
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}