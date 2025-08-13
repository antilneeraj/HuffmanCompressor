plugins {
    id("java")
}

group = "com.huffman"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

buildDir = file("out")

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    destinationDirectory.set(file("$buildDir/libs"))
}