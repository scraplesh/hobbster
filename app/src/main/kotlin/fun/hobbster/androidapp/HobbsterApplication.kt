package `fun`.hobbster.androidapp

import android.app.Application
import `fun`.hobbster.androidapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HobbsterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDi()
    }

    private fun setupDi() {
        startKoin {
            androidLogger()
            androidContext(this@HobbsterApplication)
            modules(koinModules)
        }
    }
}
