package me.alfredobejarano.productlisting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import me.alfredobejarano.productlisting.databinding.FragmentPostBinding
import me.alfredobejarano.productlisting.utilities.Injector
import me.alfredobejarano.productlisting.utilities.fromHTML
import me.alfredobejarano.productlisting.viewmodel.PostViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass that displays
 * the details of a post.
 */
class PostFragment : Fragment() {
    @Inject
    lateinit var vmFatory: PostViewModel.Factory

    /**
     * Inflates a given layout to this fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get this fragment data binding.
        val binding = FragmentPostBinding
            .inflate(inflater, container, false)
        // Inject this fragment dependencies
        Injector.component.inject(this)
        // Retrieve the post ViewModel.
        val vm = ViewModelProviders.of(this, vmFatory)[PostViewModel::class.java]
        // Get the selected post id.
        val postId = PostFragmentArgs.fromBundle(arguments).postId
        // Set the post to the binding.
        vm.getPost(postId).observe(this, Observer {
            it?.let {
                binding.post = it
                binding.body.text = it.body.fromHTML()
                binding.picture.setImageURI(it.imageURL)
            } ?: run {
                Snackbar.make(view!!, R.string.post_not_found, Snackbar.LENGTH_SHORT).show()
            }
        })
        // Return the binding root view.
        return binding.root
    }
}
