package delly.todoapp.config


class ForbiddenException(override val message: String) : RuntimeException()

class NotFoundException(override val message: String) : RuntimeException()