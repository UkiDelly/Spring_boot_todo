package delly.todoapp.service

import delly.todoapp.domain.dto.UserLoginRequest
import delly.todoapp.domain.dto.UserRegisterRequest
import delly.todoapp.domain.entity.UserEntity
import delly.todoapp.domain.model.User
import delly.todoapp.repository.AuthRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException


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
  
  
  fun login(reqeust: UserLoginRequest): User {
    
    val user = authRepository.findByEmail(reqeust.email) ?: throw HttpServerErrorException(
      HttpStatus.NOT_FOUND,
      "가입되어 있지 않은 유저입니다."
    )
    
    if (!passwordEncoder.matches(reqeust.password, user.password)) {
      throw HttpServerErrorException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.")
    }
    
    return user.toUser()
  }
}