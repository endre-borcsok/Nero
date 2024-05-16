pluginManagement {
    includeBuild("build-logic")
    repositories {
        google ()
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Nero"
include(":app")
include(":feature:home")
include(":feature:stocks")
include(":core:common")
include(":core:domain")
include(":core:converter:stocks:hl")
include(":core:data:stocks")
include(":core:database:stocks")
include(":core:network:base")
include(":core:network:stocks")
include(":core:model:stocks")
include(":core:testing:android")
include(":core:testing:jvm")
include(":core:testing:screenshot")
include(":core:ui:base")
include(":core:ui:stocks")
include(":core:ui:stocks:model")
