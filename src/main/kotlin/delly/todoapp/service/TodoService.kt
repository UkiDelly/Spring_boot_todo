package delly.todoapp.service

import delly.todoapp.config.ForbiddenException
import delly.todoapp.config.NotFoundException
import delly.todoapp.domain.entity.TodoEntity
import delly.todoapp.domain.model.Todo
import delly.todoapp.repository.AuthRepository
import delly.todoapp.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class TodoService(private val repository: TodoRepository, private val authRepository: AuthRepository) {
  
  @Transactional
  fun createTodo(content: String, userId: Long): Todo {
    val user = authRepository.findById(userId).orElseThrow { throw NotFoundException("User not found") }
    val newTodo = TodoEntity(content = content, creator = user)
    val todo = repository.save(newTodo).toTodo()
    return todo
  }
  
  @Transactional
  fun getTodos(userId: Long): List<Todo> {
    val user = authRepository.findById(userId).orElseThrow { throw NotFoundException("존재하지 않는 유저입니다.") }
    val todos = repository.findAllByCreatorIs(user)
    return todos.map { it.toTodo() }
  }
  
  @Transactional
  fun getTodo(todoId: Long, userId: Long): Todo {
    val todo = repository.findByIdAndCreatorId(todoId, userId) ?: throw NotFoundException("존재하지 않는 Todo입니다.")
    return todo.toTodo()
  }
  
  @Transactional
  fun updateTodoContent(todoId: Long, content: String, userId: Long): Todo {
    val todo = repository.findById(todoId).orElseThrow { throw NotFoundException("Todo not found") }
    if (todo.creator.id != userId) {
      throw ForbiddenException("작성자만 수정할 수 있습니다.")
    }
    
    todo.content = content
    val updatedTodo = repository.save(todo).toTodo()
    return updatedTodo
  }
  
  @Transactional
  fun updateTodoIsDone(todoId: Long, isDone: Boolean, userId: Long): Todo {
    val todo = repository.findById(todoId).orElseThrow { throw NotFoundException("Todo not found") }
    if (todo.creator.id != userId) {
      throw ForbiddenException("작성자만 수정할 수 있습니다.")
    }
    
    todo.isDone = isDone
    val updatedTodo = repository.save(todo).toTodo()
    return updatedTodo
  }
  
  @Transactional
  fun deleteTodoById(todoId: Long, userId: Long): Long {
    val todo = repository.findById(todoId).orElseThrow { throw NotFoundException("Todo not found") }
    if (todo.creator.id != userId) {
      throw ForbiddenException("작성자만 삭제할 수 있습니다.")
    }
    
    repository.deleteById(todoId)
    
    return todoId
  }
}