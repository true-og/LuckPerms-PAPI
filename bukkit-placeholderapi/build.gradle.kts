import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation(project(":common"))
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName.set("LuckPerms-PAPI-${project.version}.jar")
    destinationDirectory.set(file("${rootProject.projectDir}/build/libs"))
}
