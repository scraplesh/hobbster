package `fun`.hobbster.androidapp.di

import `fun`.hobbster.features.announcements.announcementsModule

val koinModules = listOf(
    navigationModule,
    repositoryModule,
    useCasesModule,
    featuresModule,
    announcementsModule
)
