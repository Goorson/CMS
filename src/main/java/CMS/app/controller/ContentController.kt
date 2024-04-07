package CMS.app.controller

import CMS.app.entity.Product
import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import CMS.app.service.SiteService
import CMS.app.service.impl.UserDetailsServiceImpl
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("/products/save")
    fun addProduct(
        @RequestParam("productName") name: String,
        @RequestParam("productDescription") description: String,
        @RequestParam("productPrice") price: Double,
        @RequestParam("productCategory") categoryId: Int,
        @RequestParam("productImage", required = false) picture: MultipartFile?,
        redirectAttributes: RedirectAttributes
    ): String {
        if (picture != null && !picture.isEmpty) {
            if (picture.isEmpty || picture.size > 5_000_000) {
                return "redirect:/main"
            } else if (!picture.contentType?.startsWith("image/")!!) {
                return "redirect:/main"
            }
            try {
                val logoFileName = UUID.randomUUID().toString() + "." + picture.originalFilename?.split(".")?.last()
                val uploadDir = Paths.get("uploads")
                Files.createDirectories(uploadDir)
                val filePath = uploadDir.resolve(logoFileName)
                picture.transferTo(filePath)

                return logoFileName
            } catch (e: Exception) {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to upload picture.")
                return "redirect:/main"
            }
        }

        val product = Product(
            id = 0,
            name = name,
            description = description,
            categoryId = categoryId,
            price = price,
            picture = ""
        )

        productService.saveProduct(product)

        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!")

        return "redirect:/main"
    }

    @PostMapping("/products/delete/{id}")
    fun deleteProduct(
        @PathVariable id: Int,
        redirectAttributes: RedirectAttributes
    ): String {
        productService.deleteProduct(id.toLong())
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!")
        return "redirect:/main"
    }



    @GetMapping("/productsList")
    fun getProductList(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())

        return "productList"
    }

    @GetMapping("/productsForm")
    fun getAddProductForm(model: Model): String {
        model.addAttribute("categories", categoryService.getAllCategories())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())

        return "productForm"
    }
    @GetMapping("/products/edit/{productId}")
    fun getEditProductForm(model: Model, @PathVariable productId: Int): String {
        model.addAttribute("categories", categoryService.getAllCategories())
        model.addAttribute("isAdmin", userDetailsServiceImpl.checkIfUserIsAdmin())

        return "productForm"
    }
}
