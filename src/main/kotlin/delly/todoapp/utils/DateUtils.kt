package delly.todoapp.utils

import java.time.LocalDateTime
import java.util.*


fun LocalDateTime.toDate(): Date {
  return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
}

fun Date.toLocalDateTime(): LocalDateTime {
  return LocalDateTime.ofInstant(this.toInstant(), java.time.ZoneId.systemDefault())
}