plugins {
    id("java-common-conventions")
}

tasks.processResources {
    expand(
        "version" to rootProject.version,
        "apiVersion" to libs.versions.paper.map { it.substringBefore('-') }.get()
    )
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":api"))

    compileOnly(libs.paper.api)
}