pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
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
include(":core:network:youtube")
include(":feature:home:main")
include(":feature:home:detail")
include(":feature:catalog")
include(":feature:map")
