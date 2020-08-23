package `fun`.hobbster.features.announcements

import `fun`.hobbster.common.didSet
import `fun`.hobbster.features.announcements.databinding.FragmentAnnouncementsBinding
import `fun`.hobbster.mviflow.UiComponent
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

class AnnouncementsUiComponent(coroutineScope: CoroutineScope) :
    UiComponent<
        AnnouncementsUiComponent.UiModel,
        AnnouncementsUiComponent.UiEvent,
        FragmentAnnouncementsBinding
        >() {

    class UiModel(val text: String)

    enum class UiEvent { ButtonClicked }

    private var announcementText: TextView by didSet {
        uiModels.map { it.text }
            .onEach(::setText)
            .launchIn(coroutineScope)
    }
    private var buttonAnnouncements: Button by didSet {
        clicks().map { UiEvent.ButtonClicked }
            .onEach(uiEvents::send)
            .launchIn(coroutineScope)
    }

    override fun bindView(view: FragmentAnnouncementsBinding) {
        announcementText = view.textviewAnnouncements
        buttonAnnouncements = view.buttonAnnouncements
    }

}
