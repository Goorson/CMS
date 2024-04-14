package CMS.app.controller

import CMS.app.service.impl.UserDetailsServiceImpl
import CMS.app.service.impl.UserServiceImpl
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping
class LoginController (
    val userServiceImpl: UserServiceImpl
        ){

    @GetMapping("/login")
    fun showLoginForm(): String {
        return "login"
    }

    @PostMapping("/register")
    fun handleRegistration(@RequestParam("username") username: String, @RequestParam("password") password: String, session: HttpSession): String {
        userServiceImpl.register(username,password)
        return "redirect:/login"
    }

    @GetMapping("/registerForm")
    fun showRegisterForm(): String {
        return "register"
    }

    @GetMapping("/custom-logout")
    fun customLogout(session: HttpSession): String {
        session.invalidate()
        return "redirect:/login"
    }
}
