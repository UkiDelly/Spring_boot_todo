package delly.todoapp.domain.entity

import delly.todoapp.domain.model.Todo
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity(name = "todo")
class TodoEntity(
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  
  @Column(nullable = false)
  var content: String,
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id", nullable = false)
  val creator: UserEntity,
  
  @CreatedDate
  val createdAt: LocalDateTime = LocalDateTime.now(),
  
  @Column(name = "is_done", nullable = false)
  var isDone: Boolean = false,
) {
  
  
  fun toTodo(): Todo = Todo(
    id = id!!,
    content = content,
    creator = creator.toUser(),
    createdAt = createdAt,
    isDone = isDone
  )
  
}