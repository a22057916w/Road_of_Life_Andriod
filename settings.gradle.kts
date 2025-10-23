/*
 * This file is the single point of truth for plugin versions and repository locations.
 */
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.13.0"
        id("org.jetbrains.kotlin.android") version "1.8.10"
    }
}

/*
 * This block configures the repositories for all sub-projects. The plugins block above
 * is used to configure the repositories for the plugins themselves.
 */
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "Road_of_Life_Andriod"
include(":app")
include(":dexguard-runtime")
include(":nhisdk_beta1.0.4")
include(":nhisdk_Beta1.0.2")
include(":nhisdk_Beta1.07")
