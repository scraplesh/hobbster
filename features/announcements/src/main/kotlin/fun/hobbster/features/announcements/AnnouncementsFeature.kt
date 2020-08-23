package `fun`.hobbster.features.announcements

import `fun`.hobbster.mviflow.Actor
import `fun`.hobbster.mviflow.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AnnouncementsFeature : FeatureViewModel<
        AnnouncementsFeature.Wish,
        AnnouncementsFeature.Wish,
        AnnouncementsFeature.Effect,
        Nothing,
        AnnouncementsFeature.State
        >(
    initialState = State(),
    wishToAction = { it },
    actor = AnnouncementsActor(),
    reducer = AnnouncementsReducer()
) {

    enum class Wish { ChangeText }

    data class State(
        val text: String = "First",
        val isLoading: Boolean = false,
        val error: Throwable? = null
    )

    sealed class Effect {
        class TextChanged(val text: String) : Effect()
        object StartedLoading : Effect()
        object ErrorOccurred : Effect()
    }

    class AnnouncementsActor : Actor<Wish, State, Effect> {
        override suspend fun invoke(action: Wish, state: State): Flow<Effect> = when (action) {
            Wish.ChangeText -> flowOf(Effect.TextChanged("Finish"))
        }
    }


    class AnnouncementsReducer : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            Effect.StartedLoading -> state.copy(isLoading = true, error = null)
            Effect.ErrorOccurred -> state.copy(error = Throwable(), isLoading = false)
            is Effect.TextChanged -> state.copy(text = effect.text)
        }
    }
}

