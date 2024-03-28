package CMS.app.controller

import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.impl.UserDetailsServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController (
    val categoryService: CategoryService,
    val productService: ProductService,
    val userDetailsServiceImpl: UserDetailsServiceImpl
){
        @GetMapping("/main")
        fun index(model: Model): String {
            model.addAttribute("categories", categoryService.getAllCategories())
            model.addAttribute("items", productService.getAllProducts())
            model.addAttribute("logo", "/path/to/your/logo.png")
            model.addAttribute("websiteName", "Your Website Name")
            model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())
            return "karolsPage"
        }
}