val loombokVersion = "1.18.30"
val jUnitBoomVersion = "5.10.0"

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
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Logs
    implementation("org.springframework.boot:spring-boot-starter-logging")

    implementation("org.projectlombok:lombok:${loombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${loombokVersion}")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")

    testImplementation(platform("org.junit:junit-bom:${jUnitBoomVersion}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}
