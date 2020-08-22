package `fun`.hobbster.features.announcements

import `fun`.hobbster.features.announcements.databinding.FragmentAnnouncementsBinding
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

class AnnouncementsView(private val coroutineScope: CoroutineScope) :
    Flow<AnnouncementsView.UiEvent>,
    FlowCollector<AnnouncementsView.ViewModel>,
    DefaultLifecycleObserver {

    class ViewModel

    sealed class UiEvent {
        object Created : UiEvent()
    }

    private val viewModelsChannel = ConflatedBroadcastChannel<ViewModel>()
    private val uiEvents = ConflatedBroadcastChannel<UiEvent>()
    private val viewModels = viewModelsChannel.asFlow()


    fun getView(viewBinding: FragmentAnnouncementsBinding): View = viewBinding.apply {
    }.root

    override fun onCreate(owner: LifecycleOwner) {
        flowOf(UiEvent.Created).onEach { event -> uiEvents.send(event) }
            .launchIn(coroutineScope)
    }

    override suspend fun emit(value: ViewModel) {
        viewModelsChannel.send(value)
    }

    override suspend fun collect(collector: FlowCollector<UiEvent>) {
        uiEvents.asFlow()
            .collect(collector)
    }
}
