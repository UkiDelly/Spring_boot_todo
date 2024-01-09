package delly.todoapp.domain.dto

import jakarta.validation.constraints.Email


data class UserRegisterRequest(
  
  @Email
  val email: String,
  val password: String,
  val name: String
)
