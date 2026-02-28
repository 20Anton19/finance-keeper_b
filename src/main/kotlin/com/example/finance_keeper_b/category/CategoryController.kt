package com.example.finance_keeper_b.category

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val service: CategoryService
) {
    @GetMapping
    fun getAllCategories(): List<CategoryDto> {
        return service.getAll()
    }

    @PostMapping
    fun postCategory(@RequestBody categoryDto: CategoryDto): List<CategoryDto> {
        return service.create(categoryDto)
    }

    @PutMapping
    fun putCategory(@RequestBody categoryDto: CategoryDto): CategoryDto {
        return service.update(categoryDto)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): List<CategoryDto> {
        return service.delete(id)
    }
}