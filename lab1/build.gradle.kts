plugins {
    id("java")
    id("pmd")
    id("jacoco")
}

group = "org.testing-lab"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

pmd {
    isConsoleOutput = true
    toolVersion = "6.54.0"
    ruleSetFiles = files("./pmd.xml")
}

