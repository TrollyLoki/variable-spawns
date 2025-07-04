plugins {
    id("java-common-conventions")
    id("maven-publish")
}

dependencies {
    compileOnly(libs.paper.api)
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("api") {
            from(components["java"])
        }
    }
}