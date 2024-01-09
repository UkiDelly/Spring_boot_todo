package delly.todoapp.domain.dto

import delly.todoapp.domain.model.Token
import delly.todoapp.domain.model.User

data class UserWithTokenResponse(val user: User, val token: Token)
