package delly.todoapp.domain.entity

import delly.todoapp.domain.model.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity(name = "users")
class UserEntity(
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  
  @Column(nullable = false, unique = true)
  val email: String,
  
  name: String,
  
  password: String,
  
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  val createdAt: LocalDateTime = LocalDateTime.now(),
  
  ) {
  @Column
  var name: String = name
    protected set
  
  @Column(nullable = false)
  var password: String = password
    protected set
  
  
  fun toUser(): User =
    User(
      id = id!!,
      email = email,
      name = name,
      createdAt = createdAt
    )
  
}