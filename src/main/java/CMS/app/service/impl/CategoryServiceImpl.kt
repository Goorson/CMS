package CMS.app.service.impl

import CMS.app.entity.Category
import CMS.app.repository.CategoryRepository
import CMS.app.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl @Autowired constructor(private val categoryRepository: CategoryRepository): CategoryService{

    override fun addCategory(category: Category): Category = categoryRepository.save(category)

    override fun getCategoryById(id: Long): Category = categoryRepository.findById(id).orElseThrow {
        RuntimeException("Category not found with id: $id")
    }

    override fun getAllCategories(): List<Category> = categoryRepository.findAll()

    override fun updateCategory(id: Long, updatedCategory: Category): Category {
        val category = getCategoryById(id)
        category.name = updatedCategory.name
        category.description = updatedCategory.description
        return categoryRepository.save(category)
    }
}
