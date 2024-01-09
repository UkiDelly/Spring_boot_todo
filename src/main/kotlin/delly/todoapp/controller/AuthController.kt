package delly.todoapp.controller

import delly.todoapp.domain.dto.UserLoginRequest
import delly.todoapp.domain.dto.UserRegisterRequest
import delly.todoapp.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {
  
  
  @PostMapping("/register")
  fun register(
    @Valid
    @RequestBody
    body: UserRegisterRequest
  ): ResponseEntity<Any> {
    
    val user = authService.register(body)
    return ResponseEntity.status(HttpStatus.CREATED).body(user)
  }
  
  @PostMapping("/login")
  fun login(@Valid @RequestBody body: UserLoginRequest): ResponseEntity<Any> {
    
    val user = authService.login(body)
    return ResponseEntity.ok(user)
    
  }
  
  @GetMapping("/jwt")
  fun jwtTest(@RequestHeader("Authorization") auth: String): ResponseEntity<Any> {
    
    val token = auth.split("Bearer ")[1]
    val userId = authService.verifyToken(token)
    println(token)
    
    return ResponseEntity.ok(userId)
    
  }
  
}
