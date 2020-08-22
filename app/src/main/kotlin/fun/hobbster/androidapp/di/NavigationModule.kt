package `fun`.hobbster.androidapp.di

import androidx.fragment.app.FragmentActivity
import `fun`.hobbster.common.navigation.Coordinator
import `fun`.hobbster.androidapp.navigation.RootCoordinator
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

val navigationModule = module {
  val cicerone = Cicerone.create()
  single { cicerone.router }
  single { RootCoordinator(router = get()) } bind Coordinator::class
  single { cicerone.navigatorHolder }
  factory<Navigator> { (activity: FragmentActivity, containerId: Int) ->
    SupportAppNavigator(activity, containerId)
  }
}
