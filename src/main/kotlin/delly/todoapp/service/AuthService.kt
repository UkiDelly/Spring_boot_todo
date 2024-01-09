package delly.todoapp.service

import delly.todoapp.domain.dto.UserRegisterRequest
import delly.todoapp.domain.entity.UserEntity
import delly.todoapp.domain.model.User
import delly.todoapp.repository.AuthRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
@Transactional
class AuthService(private val authRepository: AuthRepository, private val passwordEncoder: PasswordEncoder) {
  
  
  fun register(reqeust: UserRegisterRequest): User {
    
    val hashedPassword = passwordEncoder.encode(reqeust.password)
    
    val entity = UserEntity(
      email = reqeust.email,
      password = hashedPassword,
      name = reqeust.name
    )
    
    val result = authRepository.save(entity)
    
    return result.toUser()
  }
}