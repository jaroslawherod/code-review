plugins {
  id("org.springframework.boot") version "2.5.0"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  java
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:28.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
