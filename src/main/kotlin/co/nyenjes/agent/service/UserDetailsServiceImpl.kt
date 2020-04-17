package co.nyenjes.agent.service

import co.nyenjes.agent.domain.AuthUserDetail
import co.nyenjes.agent.repository.UserRepository
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        val user = userRepository.findByUsername(username!!)
        if (!user.isPresent)
            throw UsernameNotFoundException("Username $username is not found")

        val userDetail = AuthUserDetail(user.get())
        AccountStatusUserDetailsChecker().check(userDetail)
        return userDetail

    }

}
