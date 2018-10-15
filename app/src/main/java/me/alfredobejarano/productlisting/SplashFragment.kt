package me.alfredobejarano.productlisting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.facebook.drawee.backends.pipeline.Fresco
import me.alfredobejarano.productlisting.utilities.Injector
import me.alfredobejarano.productlisting.viewmodel.SessionViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashFragment : Fragment() {
    @Inject
    lateinit var vmFactory: SessionViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.theme?.applyStyle(R.style.SplashTheme, true)
        return View(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize fresco
        Fresco.initialize(requireContext())
        // Initialize the app dagger injector.
        Injector.initialize(activity?.application!!)
        // Then, proceed to inject the dependencies for this fragment.
        Injector.component.inject(this)
        // Retrieve a SessionViewModel for this fragment.
        val vm = ViewModelProviders.of(this, vmFactory)[SessionViewModel::class.java]
        // Proceed to observe the fetch of a session in cache.
        vm.session.observe(this, Observer {
            NavHostFragment.findNavController(this)
                .navigate(
                    if (it?.isNotEmpty() == true)
                        R.id.action_splashFragment_to_postsFragment
                    else
                        R.id.action_splashFragment_to_loginFragment
                )
        })
    }
}
