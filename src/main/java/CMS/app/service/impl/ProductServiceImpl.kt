package CMS.app.service.impl

import CMS.app.entity.Product
import CMS.app.repository.ProductRepository
import CMS.app.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(@Autowired private val productRepository: ProductRepository) : ProductService {
    // Implement the methods here
    override fun saveProduct(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Long) {
        TODO("Not yet implemented")
    }

    override fun updateProduct(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Long): Product? {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }
}
