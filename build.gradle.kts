
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.cio.EngineMain"
}

repositories {
    mavenCentral()
}

tasks.create("buildUberJar", type = Jar::class) {
    manifest {
        attributes["Main-Class"] = "io.ktor.server.cio.EngineMain"
    }
    archiveFileName.set("college_backend-all.jar")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    //implementation(libs.h2)
    //implementation(libs.ktor.server.openapi)
    //implementation(libs.ktor.server.swagger)
    implementation(libs.postgresql)
    implementation(libs.ktor.server.cio)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("com.zaxxer:HikariCP:6.3.2")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    //implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.36")
    implementation("io.ktor:ktor-server-cors:3.2.2")


}
