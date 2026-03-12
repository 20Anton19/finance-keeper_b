package com.example.finance_keeper_b.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
class UserService(
    private val userRepo: UserRepo
): UserDetailsService {
    fun getAll(): List<UserDto> {
        return userRepo.findAll().map { it.toDto() }
    }

    fun create(userDto: UserDto): List<UserDto> {
        userRepo.save(userDto.toEntity())
        return userRepo.findAll().map { it.toDto() }
    }

    fun update(userDto: UserDto): UserDto {
        if (userRepo.findById(userDto.id).orElse(null) != null)
            userRepo.save(userDto.toEntity())
        return userRepo.getReferenceById(userDto.id).toDto()
    }

    fun delete(id: Long): List<UserDto> {
        userRepo.deleteById(id)
        return userRepo.findAll().map { it.toDto() }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepo.findByLogin(username)
            ?: throw UsernameNotFoundException("Пользователь $username не найден")
    }
}
