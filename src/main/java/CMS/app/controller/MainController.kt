package CMS.app.controller

import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.SiteService
import CMS.app.service.impl.UserDetailsServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.nio.file.Paths

@Controller
class MainController (
    val categoryService: CategoryService,
    val productService: ProductService,
    val userDetailsServiceImpl: UserDetailsServiceImpl,
    val siteService: SiteService
){
        @GetMapping("/main")
        fun index(model: Model): String {
            val site = siteService.getSiteData()
            model.addAttribute("logo", site.logo)
            model.addAttribute("websiteName", site.name)
            model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())

            val path = Paths.get("uploads").toAbsolutePath().toString()
            return "karolsPage"
        }
}