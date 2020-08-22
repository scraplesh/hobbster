package `fun`.hobbster.mviflow

import kotlinx.coroutines.flow.Flow

interface Actor<Action, State, Effect> {
  suspend operator fun invoke(action: Action, state: State): Flow<Effect>
}
