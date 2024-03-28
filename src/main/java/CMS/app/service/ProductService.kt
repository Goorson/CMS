package CMS.app.service

import CMS.app.entity.Product
import CMS.app.entity.ProductStructure

interface ProductService {
    fun saveProduct(product: Product): Product
    fun deleteProduct(id: Long)
    fun updateProduct(product: Product): Product
    fun getProductById(id: Long): Product?
    fun getAllProducts(): List<Product>
    fun getAllProductStructures(): List<ProductStructure>
}
