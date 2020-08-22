package `fun`.hobbster.mviflow

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State
