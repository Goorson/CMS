package CMS.app.service.impl

import CMS.app.entity.User
import CMS.app.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User as SpringUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
){

    fun user(){
        val user = User(
            1,
            "admin",
            passwordEncoder.encode("pass"),
            "ADMIN"
        )
        userRepository.save(user)
    }
}