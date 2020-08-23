package `fun`.hobbster.features.announcements

import `fun`.hobbster.mviflow.Actor
import `fun`.hobbster.mviflow.Bootstrapper
import `fun`.hobbster.mviflow.Feature
import `fun`.hobbster.mviflow.NewsPublisher
import `fun`.hobbster.mviflow.PostProcessor
import `fun`.hobbster.mviflow.Reducer
import `fun`.hobbster.mviflow.WishToAction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

abstract class FeatureViewModel<Wish, Action, Effect, News, State>(
  initialState: State,
  private val wishToAction: WishToAction<Wish, Action>,
  private val bootstrapper: Bootstrapper<Action>? = null,
  private val actor: Actor<Action, State, Effect>,
  private val reducer: Reducer<State, Effect>,
  private val newsPublisher: NewsPublisher<Action, Effect, State, News>? = null,
  private val postProcessor: PostProcessor<Action, Effect, State>? = null
) :
  ViewModel(),
  Feature<Wish, State, News> {

  private val wishesChannel = ConflatedBroadcastChannel<Wish>()
  private val actionsChannel = ConflatedBroadcastChannel<Action>()
  private val newsChannel = ConflatedBroadcastChannel<News>()

  override var state = initialState
  override val news: Flow<News> get() = newsChannel.asFlow()

  override suspend fun collect(collector: FlowCollector<State>) {
    flowOf(
      flowOf(state),
      flowOf(
        bootstrapper?.invoke() ?: emptyFlow(),
        wishesChannel.asFlow().map { wish -> wishToAction(wish) },
        actionsChannel.asFlow()
      )
        .flattenMerge()
        .flatMapConcat { action -> actor(action, state).map { effect -> action to effect } }
        .map { (action, effect) ->
          state = reducer(state, effect)
          newsPublisher?.invoke(action, effect, state)?.let { newsChannel.send(it) }
          postProcessor?.invoke(action, effect, state)?.let { actionsChannel.send(it) }

          state
        }
    )
      .flattenMerge()
      .onEach(collector::emit)
      .launchIn(viewModelScope)
  }

  override suspend fun emit(value: Wish) {
    wishesChannel.send(value)
  }
}