package delly.todoapp.controller

import delly.todoapp.config.TokenProvider
import delly.todoapp.domain.model.Todo
import delly.todoapp.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/todos")
class TodoController(private val jwtProvider: TokenProvider, private val todoService: TodoService) {
  
  @PostMapping
  fun createTodo(
    @RequestHeader("Authorization") rawToken: String,
    @RequestBody(required = true) content: String
  ): ResponseEntity<Todo> {
    val accessToken = rawToken.split(" ")[1]
    val userId = jwtProvider.validateToken(accessToken, "access")
    val newTodo = todoService.createTodo(content, userId)
    
    return ResponseEntity.status(HttpStatus.CREATED).body(newTodo)
  }
}