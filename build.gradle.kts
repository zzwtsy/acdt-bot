plugins {
    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.0"
}

group = "com.acdt"
version = "0.1.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies{
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    implementation("org.jsoup:jsoup:1.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
}
