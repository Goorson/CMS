package CMS.app.service

import CMS.app.entity.Category

interface CategoryService {
    fun addCategory(category: Category): Category

    fun getAllCategories(): List<Category>

    fun updateCategory(id: Int, updatedCategory: Category): Category
    fun getCategoryById(id: Int): Category?
}