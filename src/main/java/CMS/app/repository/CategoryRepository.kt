package CMS.app.repository

import CMS.app.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>{
    fun findOneById(id: Int): Category?
}