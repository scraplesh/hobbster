package `fun`.hobbster.mviflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

interface Store<Wish, State> : FlowCollector<Wish>, Flow<State> {
  val state: State
}
