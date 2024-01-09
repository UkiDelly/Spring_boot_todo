package delly.todoapp.domain.dto

import jakarta.validation.constraints.Email


data class UserLoginRequests(
  
  @Email
  val email: String,
  val password: String
)
