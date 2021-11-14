import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val kotlinVersion by extra { "1.5.30" }
val kotlinCoroutineVersion by extra { "1.5.2" }

val profile = if (project.hasProperty("profile")) project.property("profile").toString() else "dev"
val mainClassName = "club.cplab.core.CoreApplicationKt"
val httpPort = 8080
val grpcPort = 9090

sourceSets {
	main {
		resources {
			srcDir("/src/main/resources")
		}
	}
}

plugins {
	val kotlinVer = "1.3.71"
	java
	idea
	application
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.allopen") version kotlinVer
	kotlin("plugin.noarg") version kotlinVer
	id("com.google.cloud.tools.jib") version "3.1.4"
	id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

val loggerVer = "2.0.4"

repositories { mavenCentral() }

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation(kotlin("reflect", kotlinVersion))
	implementation(kotlin("stdlib-jdk8", kotlinVersion))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinCoroutineVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	implementation("io.github.microutils:kotlin-logging:$loggerVer")
	implementation("org.modelmapper:modelmapper:2.4.4")
	implementation("com.auth0:java-jwt:3.18.2")
	implementation("com.fasterxml.jackson.core:jackson-core:2.13.0")
	implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
	/*swagger*/
	implementation("org.springdoc:springdoc-openapi-kotlin:1.5.11")
	implementation("org.springdoc:springdoc-openapi-webflux-ui:1.5.11")
	implementation("org.springdoc:springdoc-openapi-security:1.5.11")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "16"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.wrapper {
	gradleVersion = "7.2"
}

tasks.getByName<BootJar>("bootJar") {
	enabled = true
}