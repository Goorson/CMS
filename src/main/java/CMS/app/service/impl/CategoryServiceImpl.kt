package CMS.app.service.impl

import CMS.app.entity.Category
import CMS.app.repository.CategoryRepository
import CMS.app.service.CategoryService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CategoryServiceImpl(
        val categoryRepository: CategoryRepository
): CategoryService{

    override fun addCategory(category: Category): Category = categoryRepository.save(category)

    override fun getCategoryById(id: Int): Category {
        return categoryRepository.findOneById(id) ?: throw RuntimeException()
    }

    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun updateCategory(id: Int, updatedCategory: Category): Category {
        val category = getCategoryById(id)
        category.name = updatedCategory.name
        category.description = updatedCategory.description
        return categoryRepository.save(category)
    }
}
