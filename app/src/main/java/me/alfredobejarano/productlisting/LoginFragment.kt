package me.alfredobejarano.productlisting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import me.alfredobejarano.productlisting.data.LoginRequest
import me.alfredobejarano.productlisting.utilities.Injector
import me.alfredobejarano.productlisting.viewmodel.LoginViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass that handles a user logging in.
 *
 */
class LoginFragment : Fragment() {

    @Inject
    lateinit var vmFactory: LoginViewModel.Factory

    /**
     * Inflates the layout for this fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    /**
     * Inject dependencies when this fragment becomes visible to the user.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inject this fragment dependencies.
        Injector.component.inject(this)
        // Fetch a LoginViewModel object for this fragment.
        val vm = ViewModelProviders.of(this, vmFactory)[LoginViewModel::class.java]
        // Observe the results for a login request.
        vm.loginRespose.observe(this, Observer {
            if (it == true) {
                // If the request was successful (true), go to the PostsList fragment.
                findNavController(this).navigate(R.id.action_loginFragment_to_postsFragment)
            } else {
                // If not, display to the user that the login failed.
                Snackbar.make(view, R.string.login_failed, Snackbar.LENGTH_SHORT).show()
            }
        })
        // Set a click listener to the login button.
        login_button?.setOnClickListener {
            // Build a login request using the input from the login fields.
            val request = LoginRequest(
                username_input?.text?.toString() ?: "",
                password_input?.text?.toString() ?: ""
            )
            // Then use the ViewModel to perform said request.
            vm.requestLogin(request)
        }
    }
}
