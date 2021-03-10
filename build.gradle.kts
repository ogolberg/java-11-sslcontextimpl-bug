import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
}

group = "og.jdk.test"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    test {
        testLogging {
            events("passed", "skipped", "failed", "standard_out", "standard_error")
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}
