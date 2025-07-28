val springdocVersion            = "3.18.0"

plugins {
    id("java")
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "es.tcamacho.dev"
version = "1.0-SNAPSHOT"
//sourceCompatibility = '19'

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")

    // Logs
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springdocVersion}")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}