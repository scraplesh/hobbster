package `fun`.hobbster.mviflow

import kotlinx.coroutines.flow.Flow

typealias Bootstrapper<Action> = () -> Flow<Action>
