package `fun`.hobbster.androidapp.navigation

import `fun`.hobbster.common.navigation.Coordinator
import `fun`.hobbster.common.navigation.NavigationEvent
import ru.terrakok.cicerone.Router

class RootCoordinator(private val router: Router) : Coordinator {
  override fun accept(t: NavigationEvent) {
    when (t) {
      HobbsterNavigationEvent.ApplicationStarted -> {
        router.newRootScreen(HobbsterScreen.Announcements)
      }
    }
  }
}
