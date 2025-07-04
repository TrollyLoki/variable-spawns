plugins {
    id("java-common-conventions")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project("bukkit"))
    implementation(project("bungee"))
}