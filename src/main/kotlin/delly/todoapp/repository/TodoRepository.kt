package delly.todoapp.repository

import delly.todoapp.domain.entity.TodoEntity
import delly.todoapp.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TodoRepository : JpaRepository<TodoEntity, Long> {
  
  fun findAllByCreatorIs(creator: UserEntity): List<TodoEntity>
  
  fun findByIdAndCreatorId(todoId: Long, userId: Long): TodoEntity?
}