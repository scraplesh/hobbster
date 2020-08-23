package `fun`.hobbster.androidapp

import android.app.Application
import `fun`.hobbster.androidapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HobbsterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDi()
    }

    private fun setupDi() {
        startKoin {
            androidLogger()
            androidContext(this@HobbsterApplication)

            // region kotiln 1.4 fix
            // todo: remove after koin version update
//            modules(koinModules)
            koin.loadModules(koinModules)
            koin.createRootScope()
            // endregion
        }
    }
}
