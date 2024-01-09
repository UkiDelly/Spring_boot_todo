package delly.todoapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
  
  // 임시로 모든 요청에 대해 인증을 통과시키는 설정
  @Bean
  fun webSurfaceSecurity(): WebSecurityCustomizer {
    return WebSecurityCustomizer { webSecurity ->
      webSecurity.ignoring().anyRequest()
    }
    
    
  }
  
  // 임시로 모든 요청에 대해 인증을 통과시키는 설정
  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    
    http
      .csrf { it.disable() }
      .sessionManagement { it.disable() }
      .authorizeHttpRequests { request ->
        request.anyRequest().permitAll()
      }
    return http.build()
  }
  
  
  // 비밀번호 암호화를 위한 PasswordEncoder 빈 등록
  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
  
}