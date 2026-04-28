plugins {
    id("base")
    id("com.gradleup.shadow") version "8.3.9" apply false
}

defaultTasks("build")

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "eclipse")
    apply(plugin = "com.gradleup.shadow")

    group = "me.lucko.luckperms.placeholders"
    version = "5.5-SNAPSHOT"

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.GRAAL_VM)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(17)
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
        options.compilerArgs.add("-Xlint:deprecation")
    }

    tasks.withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.maven.apache.org/maven2/") }
        maven { url = uri("https://repo.lucko.me/") }
        maven { url = uri("https://repo.purpurmc.org/snapshots") }
        maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
    }

    dependencies {
        "compileOnly"("net.luckperms:api:5.5")
    }

    tasks.named("assemble") { dependsOn(tasks.named("shadowJar")) }

    configure<org.gradle.plugins.ide.eclipse.model.EclipseModel> {
        project.name = "${project.name}-${rootProject.name}"
    }
}
