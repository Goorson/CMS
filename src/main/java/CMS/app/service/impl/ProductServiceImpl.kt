package CMS.app.service.impl

import CMS.app.entity.Product
import CMS.app.entity.ProductStructure
import CMS.app.repository.ProductRepository
import CMS.app.service.CategoryService
import CMS.app.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val categoryService: CategoryService,
) : ProductService {
    override fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }

    override fun deleteProduct(id: Long) {
        productRepository.deleteById(id)
    }

    override fun updateProduct(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Long): Product? {
        return productRepository.findOneById(id)
    }

    override fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    override fun getAllProductStructures(): List<ProductStructure> {
        val categories = categoryService.getAllCategories()
        return productRepository.findAll().mapNotNull {
            ProductStructure(
                it.id,
                it.name,
                it.description,
                categories.find { cat -> it.categoryId == cat.id }?.name.orEmpty(),
                it.price,
                it.picture
            )
        }
    }

    fun gowno(){

    }
}
