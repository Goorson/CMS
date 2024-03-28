package CMS.app.controller

import CMS.app.service.impl.UserDetailsServiceImpl
import CMS.app.service.impl.UserServiceImpl
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class LoginController (
    val userServiceImpl: UserServiceImpl
        ){

    @GetMapping("/login")
    fun showLoginForm(): String {
        return "login"
    }

    @GetMapping("/custom-logout")
    fun customLogout(session: HttpSession): String {
        session.invalidate()
        return "redirect:/login"
    }

    @GetMapping("/user")
    fun user() {
        userServiceImpl.user()
    }

}
