package CMS.app.service.impl

import CMS.app.entity.User
import CMS.app.repository.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User as SpringUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import java.lang.RuntimeException

@Service
class UserDetailsServiceImpl(
    val userRepository: UserRepository,
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw RuntimeException("siema")
        val authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role}"))
        return SpringUser(user.username, user.password, authorities)
    }


    fun checkIfUserIsAdmin(): Boolean {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            val roleWithPrefix = "ROLE_ADMIN"
            return authentication.getAuthorities().contains(SimpleGrantedAuthority(roleWithPrefix))
        }
        return false
    }

}
