package `fun`.hobbster.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `fun`.hobbster.common.navigation.Coordinator
import `fun`.hobbster.androidapp.navigation.HobbsterNavigationEvent
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

class MainActivity : AppCompatActivity() {
  private val coordinator: Coordinator by inject()
  private val navigatorHolder: NavigatorHolder by inject()
  private val navigator: Navigator by inject {
    parametersOf(this, R.id.framelayout_container)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    coordinator.accept(HobbsterNavigationEvent.ApplicationStarted)
  }

  override fun onResumeFragments() {
    super.onResumeFragments()
    navigatorHolder.setNavigator(navigator)
  }

  override fun onPause() {
    super.onPause()
    navigatorHolder.removeNavigator()
  }
}
