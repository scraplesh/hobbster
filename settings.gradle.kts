include(
    ":app",
    ":data",
    ":domain",
    ":common",
    ":mviflow",
    // region features
    ":feature-announcements"
    // endregion
)

project(":feature-announcements").projectDir = File(settingsDir, "/features/announcements")