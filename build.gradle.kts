import com.google.protobuf.gradle.*

plugins {
    id("com.google.protobuf") version "0.9.4"
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-core:1.57.2")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.57.2")
    implementation("io.grpc:grpc-stub:1.57.2")
    implementation("io.grpc:grpc-testing:1.57.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
        }
        id("grpc-kotlin") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpc-kotlin")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
