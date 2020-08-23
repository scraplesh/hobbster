package `fun`.hobbster.features.announcements

import `fun`.hobbster.features.announcements.databinding.FragmentAnnouncementsBinding
import `fun`.hobbster.mviflow.Bindings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.core.parameter.parametersOf
import org.koin.androidx.scope.lifecycleScope as koinLifecycleScope

class AnnouncementsFragment : Fragment() {
  private val uiComponent: AnnouncementsUiComponent by koinLifecycleScope.inject {
    parametersOf(lifecycleScope)
  }
  private val bindings: Bindings<AnnouncementsUiComponent> by koinLifecycleScope.inject {
    parametersOf(lifecycleScope, this)
  }
  private lateinit var binding: FragmentAnnouncementsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindings.setup(uiComponent)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentAnnouncementsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    uiComponent.bindView(binding)
  }
}
