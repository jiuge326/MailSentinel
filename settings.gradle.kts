pluginManagement {
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
        // ML Kit 需要这个仓库
        maven { url = uri("https://maven.google.com") }
    }
}

rootProject.name = "MailSentinel"
include(":app")
