import java.util.Properties

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

val localProperties = Properties().apply {
    load(file("secrets.properties").inputStream())
}
val mapboxSecretToken: String? = localProperties.getProperty("MapboxSecretKey")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                // Do not change the username below.
                // This should always be "mapbox" (not your username).
                username = "mapbox"
                password = mapboxSecretToken ?: "mapboxSecretToken"
            }
        }
    }
}

rootProject.name = "Yamato"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":core:data")
include(":feature")
include(":feature:mountain")
include(":core:ui")
include(":core:designsystem")
include(":core:database")
include(":core:common")
include(":core:network")
include(":core:model")
include(":core:network:yamato")
include(":core:network:youtube")
include(":feature:catalog")
include(":feature:map")
include(":feature:video")
include(":feature:home")
