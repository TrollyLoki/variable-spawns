plugins {
    id("java-common-conventions")
}

tasks.processResources {
    expand("version" to rootProject.version)
}

repositories {
    maven {
        // for brigadier
        url = uri("https://libraries.minecraft.net")
    }
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:1.21-R0.2")
    implementation(project(":shared"))
}