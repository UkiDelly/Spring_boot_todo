package delly.todoapp.config

import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException


@RestControllerAdvice
class ExceptionHandler {
  
  
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
    
    val bindingResults = mutableMapOf<String, Any>()
    
    ex.bindingResult.allErrors.forEach { error ->
      val field = (error as FieldError).field
      val message = error.defaultMessage
      bindingResults[field] = message!!
    }
    
    return ResponseEntity.badRequest().body(bindingResults)
  }
  
  @ExceptionHandler(HttpServerErrorException::class)
  fun handleServerException(ex: HttpServerErrorException): ResponseEntity<Any> {
    return ResponseEntity.status(ex.statusCode).body(ex.message)
  }
}