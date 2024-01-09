package delly.todoapp.domain.model

import java.time.LocalDateTime


data class Todo(
  val id: Long,
  val content: String,
  val creator: User,
  val createdAt: LocalDateTime,
  val isDone: Boolean
)
