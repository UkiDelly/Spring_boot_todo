package delly.todoapp.config

import delly.todoapp.domain.model.Token
import delly.todoapp.utils.toDate
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class TokenProvider {
  
  @Value("\${jwt.secret}")
  private lateinit var secretKey: String
  private val key by lazy { Keys.hmacShaKeyFor(secretKey.toByteArray()) }
  
  fun generateToken(userId: Long): Token {
    
    var expireAt = LocalDateTime.now().plusDays(1)
    
    val accessToken = Jwts.builder()
      
      .subject(userId.toString())
      .claim("type", "access")
      .expiration(expireAt.toDate())
      .issuedAt(LocalDateTime.now().toDate())
      .signWith(key)
      .compact()
    
    expireAt = LocalDateTime.now().plusMonths(6)
    
    val refreshToken = Jwts.builder()
      .subject(userId.toString())
      .claim("type", "refresh")
      .expiration(expireAt.toDate())
      .issuedAt(LocalDateTime.now().toDate())
      .signWith(key)
      .compact()
    
    return Token(accessToken, refreshToken)
  }
  
  fun validateToken(token: String, type: String): Long {
    
    try {
      // require 메소드를 통해 해당 claim이 존재하는지 확
      val parser = Jwts.parser().verifyWith(key).require("type", type).build().parseSignedClaims(token)
      // reade the subject
      val subject = parser.payload.subject
      println(subject)
      return subject.toLong()
    } catch (e: JwtException) {
      throw ForbiddenException("토큰이 유효하지 않습니다.")
    }
    
  }
}