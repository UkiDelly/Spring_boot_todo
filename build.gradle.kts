import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.1"
  id("io.spring.dependency-management") version "1.1.4"
  kotlin("jvm") version "1.9.21"
  kotlin("plugin.spring") version "1.9.21"
  kotlin("plugin.jpa") version "1.9.21"
}

group = "delly"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}


allOpen {
  annotations("javax.persistence.Entity", "javax.persistence.Embeddable", "javax.persistence.MappedSuperclass")
}

noArg {
  annotations("javax.persistence.Entity", "javax.persistence.Embeddable", "javax.persistence.MappedSuperclass")
}

dependencies {
  
  // Spring
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-web")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.boot:spring-boot-starter-security")
  
  // Serialization
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  
  // Database
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  runtimeOnly("org.postgresql:postgresql")
  
  // JWT
  implementation("io.jsonwebtoken:jjwt-api:0.12.3")
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
  
  
  // Test
  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
