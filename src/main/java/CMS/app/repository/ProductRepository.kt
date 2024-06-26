package CMS.app.repository

import CMS.app.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>{
    fun findOneById(id: Long): Product?
}