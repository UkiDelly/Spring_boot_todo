package delly.todoapp.service

import delly.todoapp.repository.AuthRepository
import org.springframework.stereotype.Service


@Service
class AuthService(private val authRepository: AuthRepository)