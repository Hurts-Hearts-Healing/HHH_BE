plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.dsm"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// paseto
	implementation("dev.paseto:jpaseto-api:0.7.0")
	implementation("dev.paseto:jpaseto-impl:0.7.0")
	implementation("dev.paseto:jpaseto-jackson:0.7.0")
	implementation("dev.paseto:jpaseto-bouncy-castle:0.7.0")

	// web
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// mongodb
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

	// annotation
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// mail
	implementation("org.springframework.boot:spring-boot-starter-mail:3.1.2")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
