package `fun`.hobbster.features.announcements

import `fun`.hobbster.features.announcements.databinding.FragmentAnnouncementsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import `fun`.hobbster.mviflow.Bindings
import org.koin.core.parameter.parametersOf
import org.koin.androidx.scope.lifecycleScope as koinLifecycleScope

class AnnouncementsFragment : Fragment() {
  private val mviView: AnnouncementsView by koinLifecycleScope.inject { parametersOf(lifecycleScope) }
  private val bindings: Bindings<AnnouncementsView> by koinLifecycleScope.inject {
    parametersOf(lifecycleScope, requireContext())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindings.setup(mviView)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = mviView.getView(FragmentAnnouncementsBinding.inflate(inflater, container, false))

}

