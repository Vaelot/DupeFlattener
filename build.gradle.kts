plugins {
    id("java")
    id("idea")
    kotlin("jvm") version "1.4.0"
}

group = "xyz.vaelot"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.apache.commons:commons-compress:1.20")
    implementation("commons-codec:commons-codec:1.15")
    implementation("commons-cli:commons-cli:1.4")
    implementation("org.kocakosm:jblake2:0.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0-M1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0-M1")
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}