import com.google.protobuf.gradle.*

plugins {
    id("com.google.protobuf") version "0.9.4"
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

val jooqGeneratorRuntime by configurations.creating

dependencies {
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("io.grpc:grpc-core:1.57.2")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-netty:1.41.0")
    implementation("io.grpc:grpc-protobuf:1.57.2")
    implementation("io.grpc:grpc-stub:1.57.2")
    implementation("io.grpc:grpc-testing:1.57.2")
    implementation("org.flywaydb:flyway-core:9.21.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jooq:jooq:3.18.6")
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation(kotlin("stdlib-jdk8"))
    jooqGeneratorRuntime("org.jooq:jooq-codegen:3.18.6")
    jooqGeneratorRuntime("org.xerial:sqlite-jdbc:3.42.0.0")
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

val sourceSets = extensions.getByName("sourceSets") as SourceSetContainer
sourceSets.named("main") {
    java.srcDir("src/main/generated")
}

val mainSourceSet = sourceSets.getByName("main")

val migrateDb by tasks.registering(JavaExec::class) {
    mainClass.set("com.echo.store.EchoLogStoreKt")
    args("--migrate")
    classpath = mainSourceSet.runtimeClasspath
}

val generateJooq by tasks.registering(JavaExec::class) {
    mainClass.set("org.jooq.codegen.GenerationTool")
    classpath = configurations.getByName("jooqGeneratorRuntime")
    args("src/main/resources/jooq-config.xml")
    dependsOn("migrateDb")
}

val runProxy by tasks.registering(JavaExec::class) {
    mainClass.set("com.echo.proxy.EchoProxyKt")
    classpath = mainSourceSet.runtimeClasspath
}

val runServer by tasks.registering(JavaExec::class) {
    mainClass.set("com.echo.rpc.EchoServiceRpcKt")
    classpath = mainSourceSet.runtimeClasspath
}

val runClient by tasks.registering(JavaExec::class) {
    mainClass.set("com.echo.client.EchoClientKt")
    classpath = mainSourceSet.runtimeClasspath
    args = listOf(project.properties["args"]?.toString() ?: "")
}

val runDb by tasks.registering(JavaExec::class) {
    mainClass.set("com.echo.store.EchoLogStoreKt")
    classpath = mainSourceSet.runtimeClasspath
}
