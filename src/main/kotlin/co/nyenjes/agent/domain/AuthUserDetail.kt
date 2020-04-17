package co.nyenjes.agent.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthUserDetail(private val user: User): User(), UserDetails {

    private val grantedAuthority = ArrayList<GrantedAuthority>()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

         user.roles.forEach { role ->
            grantedAuthority.add(SimpleGrantedAuthority(role.name))
            role.permissions.forEach{ permission ->
                grantedAuthority.add(SimpleGrantedAuthority(permission.name))
            }
        }
        return grantedAuthority
    }

    override fun isEnabled(): Boolean {
        return user.isEnabled
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return user.isCredentialsNonExpired
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return user.isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return user.isAccountNonLocked
    }
}
