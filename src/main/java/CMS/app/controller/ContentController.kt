package CMS.app.controller

import CMS.app.entity.Product
import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.SiteService
import CMS.app.service.impl.UserDetailsServiceImpl
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID


@Controller
class ContentController (
    val categoryService: CategoryService,
    val productService: ProductService,
    val siteService: SiteService,
    val userDetailsServiceImpl: UserDetailsServiceImpl
        ){

    @GetMapping("/settings")
    fun getSettings(model: Model): String {
        val site = siteService.getSiteData()
        model.addAttribute("siteName", site.name)
        model.addAttribute("sitePicture", site.logo)
        return "settings"
    }

    @PostMapping("/settings/save")
    fun saveSettings(
        @RequestParam("siteName", required = false) siteName: String?,
        @RequestParam("sitePicture", required = false) sitePicture: MultipartFile?,
        redirectAttributes: RedirectAttributes
    ): String {

        val siteSettings = siteService.getSiteData()

        if (sitePicture != null && !sitePicture.isEmpty) {
            if (sitePicture.isEmpty || sitePicture.size > 5_000_000) {
                return "redirect:/main"
            } else if (!sitePicture.contentType?.startsWith("image/")!!) {
                return "redirect:/main"
            }
            try {
                val logoFileName = UUID.randomUUID().toString() + "." + sitePicture.originalFilename?.split(".")?.last()
                val uploadDir = Paths.get("uploads")
                Files.createDirectories(uploadDir)
                val filePath = uploadDir.resolve(logoFileName)
                sitePicture.transferTo(filePath)

                siteSettings.logo = logoFileName
            } catch (e: Exception) {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to upload picture.")
                return "redirect:/main"
            }
        }

        siteName?.let {
            siteSettings.name = it
        }

        siteService.changeSiteData(siteSettings)
        return "redirect:/main"
    }

    @GetMapping("/categories")
    fun getCategories(model: Model): String {
        model.addAttribute("categories", categoryService.getAllCategories())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())
        return "categories"
    }

    @GetMapping("/products")
    fun getProducts(model: Model): String {
        model.addAttribute("products", productService.getAllProductStructures())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())

        return "products"
    }

    @PostMapping("/addProduct")
    fun addProduct(
        @ModelAttribute product: Product, // Assuming you have a proper binding
        request: HttpServletRequest,
        model: Model
    ): String {
        productService.saveProduct(product) // Save your product

        model.addAttribute("products", productService.getAllProductStructures())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())


        return if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"), ignoreCase = true)) {
            "products :: productList"
        } else {
            "products"
        }
    }

}
