import com.google.protobuf.gradle.*

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    id("org.jetbrains.kotlin.kapt") version "1.5.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "2.0.3"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
    id("com.google.protobuf") version "0.8.17"
}

version = "0.1"
group = "topfarming.poc"

val kotlinVersion=project.properties.get("kotlinVersion")
val micronautVersion=project.properties.get("micronautVersion")
val junitVersion=project.properties.get("junitVersion")
val modelmapperVersion=project.properties.get("modelmapperVersion")
val protobufVersion=project.properties.get("protobufVersion")

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("topfarming.poc.*")
    }
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut.security:micronaut-security-annotations")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.mongodb:micronaut-mongo-reactive")
//    implementation("io.micronaut.cache:micronaut-cache-caffeine")
    implementation("io.micronaut.cache:micronaut-cache-core")
    implementation("io.micronaut.redis:micronaut-redis-lettuce")
//    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("io.micronaut.sql:micronaut-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.xml:micronaut-jackson-xml")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")
    compileOnly("org.graalvm.nativeimage:svm")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.testcontainers:testcontainers")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.mockito:mockito-core")

    //Modelmapper
    implementation("org.modelmapper:modelmapper:${modelmapperVersion}")

    //Protobuf
    implementation("com.google.protobuf:protobuf-kotlin:${protobufVersion}")
    implementation("io.micronaut.grpc:micronaut-protobuff-support")

    //Redis
    testImplementation("com.github.kstyrc:embedded-redis:0.6")
}


application {
    mainClass.set("topfarming.poc.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
}

sourceSets {
    main {
        proto {
            // In addition to the default 'src/main/proto'
            srcDir("src/main/protobuf")
            srcDir("src/main/protocolbuffers")
            // In addition to the default '**/*.proto' (use with caution).
            // Using an extension other than 'proto' is NOT recommended,
            // because when proto files are published along with class files, we can
            // only tell the type of a file from its extension.
            include("**/*.protodevel")
        }
        java {
            srcDirs("build/generated/source/proto/main/java")
        }
    }
    test {
        proto {
            // In addition to the default 'src/test/proto'
            srcDirs("src/test/protocolbuffers")
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

