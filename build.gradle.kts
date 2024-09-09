plugins {
    id("java")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

group = "me.rejomy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven ("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("com.github.retrooper:packetevents-spigot:2.4.0")
    compileOnly("me.clip:placeholderapi:2.11.4")
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}