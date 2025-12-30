plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = "dev.bulean"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform { defaultRepositories() }
}

dependencies {
    intellijPlatform {
        create("IC", "2024.1")       // IntelliJ Community 2024.1
        bundledPlugins(
            "com.intellij.java",      // PSI Java
            "org.jetbrains.kotlin"    // PSI Kotlin (bundled en IDEA)
        )
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)
    }
    testImplementation("junit:junit:4.13.2")
}

intellijPlatform {
    buildSearchableOptions = false
    pluginConfiguration {
        ideaVersion { sinceBuild = "241" }
        changeNotes = "Initial version"
    }
    pluginVerification { ides { recommended() } }
}

tasks {
    patchPluginXml {
        sinceBuild.set("241")
        changeNotes.set("Add a ToolWindow with ChatGPT Web embedded using JCEF.")
    }
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) }
    }
}
