package delly.todoapp.domain.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity(name = "todo")
class TodoEntity(
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  
  content: String,
  
  @ManyToOne
  @JoinColumn(name = "creator_id", nullable = false)
  val creator: UserEntity,
  
  @CreatedDate
  val createdAt: LocalDateTime = LocalDateTime.now(),
  
  isDone: Boolean = false,
) {
  
  @Column(nullable = false)
  var content: String = content
    protected set
  
  @Column(name = "is_done", nullable = false)
  var isDone: Boolean = isDone
    protected set
  
}