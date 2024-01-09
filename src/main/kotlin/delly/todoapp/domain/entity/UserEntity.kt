package delly.todoapp.domain.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity(name = "users")
class UserEntity(
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  
  @Column(nullable = false)
  val email: String,
  
  name: String,
  
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  val createdAt: LocalDateTime

) {
  @Column
  var name: String = name
    protected set
}