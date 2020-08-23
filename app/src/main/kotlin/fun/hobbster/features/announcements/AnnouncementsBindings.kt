package `fun`.hobbster.features.announcements

import `fun`.hobbster.mviflow.Bindings
import kotlinx.coroutines.CoroutineScope
import `fun`.hobbster.features.announcements.AnnouncementsFeature.Wish
import `fun`.hobbster.features.announcements.AnnouncementsUiComponent.UiModel
import `fun`.hobbster.features.announcements.AnnouncementsUiComponent.UiEvent

class AnnouncementsBindings(
  coroutineScope: CoroutineScope,
  private val feature: AnnouncementsFeature
) : Bindings<AnnouncementsUiComponent>(coroutineScope) {

  override fun setup(uiComponent: AnnouncementsUiComponent) {
    bind(feature to uiComponent using { state ->
      UiModel(text = state.text)
    })
    bind(uiComponent to feature using { uiEvent ->
      when (uiEvent) {
        UiEvent.ButtonClicked -> Wish.ChangeText
      }
    })
  }

}
