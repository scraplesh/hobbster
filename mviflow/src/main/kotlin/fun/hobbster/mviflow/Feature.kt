package `fun`.hobbster.mviflow

import kotlinx.coroutines.flow.Flow

interface Feature<Wish, State, News> : Store<Wish, State> {
  val news: Flow<News>
}
