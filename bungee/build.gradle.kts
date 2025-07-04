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
    implementation(project(":shared"))

    compileOnly(libs.bungeecord.api)
}