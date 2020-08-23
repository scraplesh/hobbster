package `fun`.hobbster.mviflow

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow

abstract class UiComponent<UiModel, UiEvent, View> : FlowCollector<UiModel>, Flow<UiEvent> {
    private val uiModelsChannel = ConflatedBroadcastChannel<UiModel>()

    protected val uiModels = uiModelsChannel.asFlow()
    protected val uiEvents = ConflatedBroadcastChannel<UiEvent>()

    abstract fun bindView(view: View)

    override suspend fun emit(value: UiModel) {
        uiModelsChannel.send(value)
    }

    override suspend fun collect(collector: FlowCollector<UiEvent>) {
        uiEvents.asFlow()
            .collect(collector)
    }
}