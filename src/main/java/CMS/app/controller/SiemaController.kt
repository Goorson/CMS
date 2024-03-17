package CMS.app.controller

import CMS.app.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/")
class SiemaController (
        @Autowired val categoryService: CategoryService
){
    @RequestMapping()
    fun greeting(@RequestParam(name = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        val categories = categoryService.getAllCategories()
        model.addAttribute("name", name)
        model.addAttribute("categories", categories)
        return "greeting"
    }
}