package `fun`.hobbster.features.announcements

import `fun`.hobbster.mviflow.Bindings
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.scope.getViewModel
import org.koin.dsl.module

val announcementsModule = module {
  scope<AnnouncementsFragment> {
    viewModel { AnnouncementsFeature() }
    scoped { (coroutineScope: CoroutineScope) -> AnnouncementsUiComponent(coroutineScope) }
    scoped<Bindings<AnnouncementsUiComponent>> { (
                                                     coroutineScope: CoroutineScope,
                                                     viewModelStoreOwner: ViewModelStoreOwner
                                                 ) ->
      val feature = getViewModel<AnnouncementsFeature>(viewModelStoreOwner)
      AnnouncementsBindings(coroutineScope, feature = get())
    }
  }
}