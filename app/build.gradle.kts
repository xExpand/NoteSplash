plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("core.Main")
    applicationName = "notesplash"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}