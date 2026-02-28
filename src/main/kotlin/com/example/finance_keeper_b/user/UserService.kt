package com.example.finance_keeper_b.user

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
class UserService(
    private val userRepo: UserRepo
) {

    fun create(userDto: UserDto): List<UserDto> {
        userRepo.save(userDto.toEntity())
        return userRepo.findAll().map { it.toDto() }
    }

    fun update(userDto: UserDto): UserDto {
        if (userRepo.findById(userDto.id).orElse(null) != null)
            userRepo.save(userDto.toEntity())
        return userRepo.getReferenceById(userDto.id).toDto()
    }
}