package CMS.app.controller

import CMS.app.entity.Category
import CMS.app.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController @Autowired constructor(private val categoryService: CategoryService) {

    @PostMapping
    fun addCategory(@RequestBody category: Category): Category =
        categoryService.addCategory(category)

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): Category =
        categoryService.getCategoryById(id)

    @GetMapping
    fun getAllCategories(): List<Category> =
        categoryService.getAllCategories()

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody updatedCategory: Category): Category =
        categoryService.updateCategory(id, updatedCategory)
}