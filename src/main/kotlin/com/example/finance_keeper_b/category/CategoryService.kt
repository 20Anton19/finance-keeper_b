package com.example.finance_keeper_b.category

import com.example.finance_keeper_b.user.UserDto
import com.example.finance_keeper_b.user.UserRepo
import com.example.finance_keeper_b.user.toDto
import com.example.finance_keeper_b.user.toEntity
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepo: CategoryRepo,
    private val userRepo: UserRepo
) {
    fun getAll(): List<CategoryDto> {
        return categoryRepo.findAll().map { it.toDto() }
    }
    fun create(categoryDto: CategoryDto): List<CategoryDto> {
        categoryRepo.save(categoryDto.toEntity(userRepo))
        return categoryRepo.findAll().map { it.toDto() }
    }

    fun update(categoryDto: CategoryDto): CategoryDto {
        if (categoryRepo.findById(categoryDto.id).orElse(null) != null)
            categoryRepo.save(categoryDto.toEntity(userRepo))
        return categoryRepo.getReferenceById(categoryDto.id).toDto()
    }

    fun delete(id: Long): List<CategoryDto> {
        categoryRepo.deleteById(id)
        return categoryRepo.findAll().map { it.toDto() }
    }
}