import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val Versions = object {
        val OPENAPI_GENERERATOR_VERSION = "6.0.1"
    }
    kotlin("jvm") version "1.7.10"
    id("org.openapi.generator") version Versions.OPENAPI_GENERERATOR_VERSION
}

group = "org.telus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools:openapi-generator:6.0.1")
    implementation("org.openapitools:openapi-generator-cli:6.0.1")
    implementation("org.openapitools:openapi-generator-gradle-plugin:6.0.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/petstore-v3.0.yaml")
    outputDir.set("$rootDir/src/")
    apiPackage.set("org.telus.com.api")
    invokerPackage.set("org.telus.com.invoker")
    modelPackage.set("org.telus.com.model")
    configOptions.apply {
        put("dateLibrary", "java17")
    }
    globalProperties.apply {
        put("modelDocs", "false")
    }
    skipValidateSpec.set(true)
    logToStderr.set(true)
}