val forgeVersion = property("forge_version").toString()
val architecturyVersion = property("architectury_version").toString()
val kotlinForgeVersion = property("kotlin_forge_version").toString()
val discordIPCVersion = property("discord_ipc_version").toString()

architectury {
    platformSetupLoomIde()
    forge()
}

base.archivesName.set("${base.archivesName.get()}-forge")

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

repositories {
    maven("https://cursemaven.com")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}

val includeLib: Configuration by configurations.creating
val includeMod: Configuration by configurations.creating

fun DependencyHandlerScope.setupConfigurations() {
    includeLib.dependencies.forEach {
        forgeRuntimeLibrary(it)
        include(it)
    }

    includeMod.dependencies.forEach {
        forgeRuntimeLibrary(it)
        include(it)
    }
}

dependencies {
    // Forge API
    forge("net.minecraftforge:forge:$forgeVersion")

    // Add dependencies on the required Kotlin modules.
    includeLib("org.reflections:reflections:0.10.2")
    includeLib("org.javassist:javassist:3.28.0-GA")
    includeLib("com.github.caoimhebyrne:KDiscordIPC:$discordIPCVersion") { isTransitive = false }

    // Add mods to the mod jar
    includeMod("thedarkcolour:kotlinforforge:$kotlinForgeVersion")

    // Fix KFF
    compileOnly(kotlin("stdlib"))

    // Common (Do not touch)
    common(project(":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) { isTransitive = false }

    // Finish the configuration
    setupConfigurations()
}

tasks {
    processResources {
        inputs.property("group", project.group)
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") {
            expand(getProperties())
            expand(mutableMapOf(
                "group" to project.group,
                "version" to project.version,
            ))
        }
    }

    sourceSets.forEach {
        val dir = layout.buildDirectory.dir("sourcesSets/${it.name}")
        it.output.setResourcesDir(dir)
        it.java.destinationDirectory.set(dir)
    }
}
