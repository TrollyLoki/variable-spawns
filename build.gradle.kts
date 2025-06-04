plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.6"
}

group = "net.trollyloki.plugins"
version = "1.2"

dependencies {
    implementation(project("bukkit"))
    implementation(project("bungee"))
}