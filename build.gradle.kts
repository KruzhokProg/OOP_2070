plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:1.13.2")
//    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}