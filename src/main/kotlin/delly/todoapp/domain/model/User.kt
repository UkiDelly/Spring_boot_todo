package delly.todoapp.domain.model

import java.time.LocalDateTime

data class User(val id: Long, val email: String, val name: String, val createdAt: LocalDateTime)
