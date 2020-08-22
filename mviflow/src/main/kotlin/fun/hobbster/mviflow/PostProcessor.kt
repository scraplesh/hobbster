package `fun`.hobbster.mviflow

typealias PostProcessor<Action, Effect, State> = (action: Action, effect: Effect, state: State) -> Action?
