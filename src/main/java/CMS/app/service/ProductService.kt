package CMS.app.service

import CMS.app.entity.Product

interface ProductService {
    fun saveProduct(product: Product): Product
    fun deleteProduct(id: Long)
    fun updateProduct(product: Product): Product
    fun getProductById(id: Long): Product?
    fun getAllProducts(): List<Product>
}
