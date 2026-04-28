import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

repositories {
    maven { url = uri("https://repo.mvdw-software.com/content/groups/public/") }
}

dependencies {
    implementation(project(":common"))
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("be.maximvdw:MVdWPlaceholderAPI:3.0.1-SNAPSHOT") {
        exclude(group = "be.maximvdw", module = "MVdWUpdater")
    }
}

tasks.named<ProcessResources>("processResources") {
    filesMatching("plugin.yml") {
        expand("pluginVersion" to project.version)
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName.set("LuckPerms-MVdWPlaceholderAPI-Hook.jar")
}
