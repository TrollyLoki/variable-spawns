plugins {
    id("java-common-conventions")
}

val apiVersion = "1.20.6"

tasks.processResources {
    expand(
        "version" to rootProject.version,
        "apiVersion" to apiVersion
    )
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$apiVersion-R0.1-SNAPSHOT")
    implementation(project(":shared"))
}