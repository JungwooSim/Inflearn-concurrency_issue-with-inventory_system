import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  id("org.springframework.boot") version "2.7.7"
  id("io.spring.dependency-management") version "1.0.15.RELEASE"
  kotlin("jvm") version "1.7.20"
  id("org.jetbrains.kotlin.plugin.jpa") version "1.7.20"
  id("org.jetbrains.kotlin.plugin.spring") version "1.7.20"
  id("org.jetbrains.kotlin.kapt") version "1.7.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  runtimeOnly("com.mysql:mysql-connector-j")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  runtimeOnly("com.h2database:h2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
  useJUnitPlatform()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = "1.8"
}