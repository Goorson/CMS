package CMS.app.controller

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class LoginController {

    @GetMapping("/login")
    fun showLoginForm(): String {
        return "login"
    }

    @GetMapping("/custom-logout")
    fun customLogout(session: HttpSession): String {
        session.invalidate() // Invalidate the session
        return "redirect:/login" // Redirect to login page
    }


}
