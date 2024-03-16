package CMS.app.controller

import CMS.app.entity.Product
import CMS.app.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    @Autowired private val productService: ProductService
) {
        @PostMapping
        fun addProduct(@RequestBody product: Product): Product =
            productService.saveProduct(product)

        @GetMapping
        fun getAllProducts(): List<Product> =
            productService.getAllProducts()

        @GetMapping("/{id}")
        fun getProductById(@PathVariable id: Long): Product? =
            productService.getProductById(id)

        @PutMapping("/{id}")
        fun updateProduct(@RequestBody product: Product): Product =
            productService.updateProduct(product)

        @DeleteMapping("/{id}")
        fun deleteProduct(@PathVariable id: Long) {
            productService.deleteProduct(id)
        }
}
