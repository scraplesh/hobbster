package `fun`.hobbster.features.announcements

import `fun`.hobbster.mviflow.Actor
import `fun`.hobbster.mviflow.Bootstrapper
import `fun`.hobbster.mviflow.MviFeature
import `fun`.hobbster.mviflow.PostProcessor
import `fun`.hobbster.mviflow.Reducer
import `fun`.hobbster.mviflow.WishToAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AnnouncementsFeature(initialState: State) :
    MviFeature<
            AnnouncementsFeature.Wish,
            AnnouncementsFeature.Action,
            AnnouncementsFeature.Effect,
            Nothing,
            AnnouncementsFeature.State
            >(
      initialState = initialState,
      wishToAction = AnnouncementsWishToAction(),
      bootstrapper = AnnouncementsBootstrapper(),
      actor = AnnouncementsActor(),
      reducer = AnnouncementsReducer(),
      postProcessor = AnnouncementsPostProcessor()
    ) {

    class Wish

    enum class Action { GetAnnouncements }

    data class State(
      val isLoading: Boolean = false,
      val error: Throwable? = null
    )

    sealed class Effect {
        object StartedLoading : Effect()
        object ErrorOccurred : Effect()
    }

    class AnnouncementsWishToAction : WishToAction<Wish, Action> {
        override fun invoke(wish: Wish): Action = Action.GetAnnouncements
    }

    class AnnouncementsBootstrapper : Bootstrapper<Action> {
        override fun invoke(): Flow<Action> = flowOf(Action.GetAnnouncements)
    }

    class AnnouncementsActor : Actor<Action, State, Effect> {
        override suspend fun invoke(action: Action, state: State): Flow<Effect> = when (action) {
          Action.GetAnnouncements -> TODO()
        }
    }


    class AnnouncementsReducer : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
          Effect.StartedLoading -> state.copy(isLoading = true, error = null)
          Effect.ErrorOccurred -> state.copy(error = Throwable(), isLoading = false)
        }
    }

    class AnnouncementsPostProcessor :
        PostProcessor<Action, Effect, State> {
        override fun invoke(action: Action, effect: Effect, state: State): Action? {
            return null
        }
    }
}

