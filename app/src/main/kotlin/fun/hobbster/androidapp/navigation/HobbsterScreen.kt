package `fun`.hobbster.androidapp.navigation

import `fun`.hobbster.features.announcements.AnnouncementsFragment
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class HobbsterScreen : SupportAppScreen() {
    object Announcements : HobbsterScreen() {
        override fun getFragment(): Fragment? = AnnouncementsFragment()
    }
}
