package com.example.securingweb

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MvcConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("home")
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/main").setViewName("main")
        registry.addViewController("/categories").setViewName("categories")
        registry.addViewController("/prodcuts").setViewName("products")
        registry.addViewController("/auth/login").setViewName("login")
    }
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads/")
    }

}