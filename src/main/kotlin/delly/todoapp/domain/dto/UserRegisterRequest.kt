package delly.todoapp.domain.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull


// 그냥 단순히 @Email , @NotNull만 붙인다고 해서 검증이 되는 것은 아닙니다.
// 코틀린의 경우 () 안에 변수를 선언하게 되면, 생성자와 생성자의 param에 어노테이션이 붙게 되고,
// 이건 @Email, @NotNull의 타겟 순서상 뒤에 있기 때문에 검증이 되지 않습니다.
// 이것을 해결하느 방법은 2가지가 있습니다.
// 1. @field:Email, @field:NotNull 이렇게 붙여주는 방법
// 2. 생성자에 어노테이션을 붙이지 않고, 필드로 선언해서 어노테이션을 붙여주는 방법


// 1.번 방법
// class UserRegisterRequest(
//
//   @field:Email(message = "이메일 형식이여야 합니다.", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
//   val email: String,
//
//   @field:NotNull(message = "비밀번호는 필수입니다.")
//   val password: String,
//   val name: String
// )

// 2.번 방법
class UserRegisterRequest {
  @Email(message = "이메일 형식이여야 합니다.")
  @NotNull
  val email: String = ""
  
  @NotNull
  val password: String = ""
  
  @NotNull
  val name: String = ""
}
