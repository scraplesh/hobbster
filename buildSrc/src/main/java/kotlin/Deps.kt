object Deps {
  // region Plugins
  const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
  const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
  const val googleServicesPlugin = "com.google.gms:google-services:${Versions.googleServicesPlugin}"

  // endregion
  // region Languages
  const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

  // endregion
  // region JetBrains
  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
  const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

  // endregion
  // region Android
  const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
  const val androidxCardView = "androidx.cardview:cardview:${Versions.androidxCardView}"
  const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
  const val androidxCoordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.androidxCoordinatorLayout}"
  const val androidxFragment = "androidx.fragment:fragment:${Versions.androidxFragment}"
  const val androidxLifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifecycle}"
  const val androidxLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
  const val androidxLifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
  const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
  const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
  const val material = "com.google.android.material:material:${Versions.material}"
  // endregion
  // region Libraries
  const val adapterDelegatesKotlinDslViewBinding = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
  const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"
  const val coil = "io.coil-kt:coil:${Versions.coil}"
  const val flowBinding = "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.flowBinding}"
  const val koinCore = "org.koin:koin-core:${Versions.koin}"
  const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
  const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
  // endregion
}
