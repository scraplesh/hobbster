package `fun`.hobbster.mviflow

typealias NewsPublisher<Action, Effect, State, News> = (action: Action, effect: Effect, state: State) -> News?
