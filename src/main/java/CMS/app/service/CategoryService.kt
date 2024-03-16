package CMS.app.service

import CMS.app.entity.Category

interface CategoryService {
    fun addCategory(category: Category): Category

    fun getCategoryById(id: Long): Category

    fun getAllCategories(): List<Category>

    fun updateCategory(id: Long, updatedCategory: Category): Category
}