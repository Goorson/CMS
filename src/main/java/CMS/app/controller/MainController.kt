package CMS.app.controller

import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.SiteService
import CMS.app.service.impl.UserDetailsServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.nio.file.Paths

@Controller
class MainController (
    val userDetailsServiceImpl: UserDetailsServiceImpl,
    val siteService: SiteService
){
        @GetMapping("/main")
        fun index(model: Model): String {
            val site = siteService.getSiteData()
            model.addAttribute("logo", site.logo)
            model.addAttribute("websiteName", site.name)
            model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())
            return "mainPage"
        }
}