package CMS.app.controller

import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.SiteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
class ContentController (
    val categoryService: CategoryService,
    val productService: ProductService,
    val siteService: SiteService,
        ){

    @GetMapping("/settings")
    fun getSettings(model: Model): String {
        val site = siteService.getSiteData()
        model.addAttribute("siteName", site.name)
        model.addAttribute("sitePicture", site.logo)
        return "settings"
    }

    // todo for further save
    @PostMapping("/settings/save")
    fun saveSettings(
        @RequestParam("siteName") siteName: String,
        @RequestParam("sitePicture") sitePicture: MultipartFile
    ): String {
        // Process and save the settings here
        // For example, save the siteName and upload the sitePicture to a storage service

        return "redirect:/main" // Redirect back to the settings page or another page
    }


    @GetMapping("/categories")
    fun getCategories(model: Model): String {
        model.addAttribute("categories", categoryService.getAllCategories())
        return "categories"
    }

    @GetMapping("/products")
    fun getProducts(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())
        return "products"
    }
}