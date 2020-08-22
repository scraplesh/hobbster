package `fun`.hobbster.mviflow

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

abstract class MviFeature<Wish, Action, Effect, News, State>(
  initialState: State,
  private val wishToAction: WishToAction<Wish, Action>,
  private val bootstrapper: Bootstrapper<Action>? = null,
  private val actor: Actor<Action, State, Effect>,
  private val reducer: Reducer<State, Effect>,
  private val newsPublisher: NewsPublisher<Action, Effect, State, News>? = null,
  private val postProcessor: PostProcessor<Action, Effect, State>? = null
) : Feature<Wish, State, News> {

  private val wishesChannel = ConflatedBroadcastChannel<Wish>()
  private val actionsChannel = ConflatedBroadcastChannel<Action>()
  private val newsChannel = ConflatedBroadcastChannel<News>()

  override var state = initialState
  override val news: Flow<News> get() = newsChannel.asFlow()

  override suspend fun collect(collector: FlowCollector<State>) {
    flowOf(
      bootstrapper?.invoke() ?: emptyFlow(),
      wishesChannel.asFlow()
        .map { wish -> wishToAction(wish) },
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
      .collect(collector)
  }

  override suspend fun emit(value: Wish) {
    wishesChannel.send(value)
  }
}
