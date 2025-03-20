pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "MovieAppOtus"
include(":app")
include(":network")
include(":core")
include(":core_api")
include(":core_impl")
include(":repository_home")
include(":feature_home")
include(":ui_kit")
include(":feature_details")
include(":database")
include(":feature_full_list")
include(":feature_search")
