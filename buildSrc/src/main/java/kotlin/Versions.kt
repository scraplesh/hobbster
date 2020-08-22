import org.gradle.api.JavaVersion

object Versions {
  // region Languages
  const val kotlin = "1.4.0"
  val java = JavaVersion.VERSION_1_8

  // endregion
  // region Plugins
  const val androidGradlePlugin = "4.0.1"
  const val kotlinGradlePlugin = kotlin
  const val googleServicesPlugin = "4.3.3"

  // endregion
  // region JetBrains
  const val coroutines = "1.3.9"

  // endregion
  // region Android
  const val androidxAppCompat = "1.2.0"
  const val androidxCardView = "1.0.0"
  const val androidxConstraintLayout = "2.0.0-rc1"
  const val androidxCoordinatorLayout = "1.1.0"
  const val androidxFragment = "1.2.4"
  const val androidxLifecycle = "2.2.0"
  const val androidxRecyclerView = "1.1.0"
  const val material = "1.2.0"

  // endregion
  // region Libraries
  const val adapterDelegates = "4.3.0"
  const val cicerone = "5.1.1"
  const val coil = "1.0.0-rc1"
  const val firebaseAnalytics = "17.4.1"
  const val flowBinding = "0.12.0"
  const val koin = "2.1.6"
}
