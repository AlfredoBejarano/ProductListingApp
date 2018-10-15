package me.alfredobejarano.productlisting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import me.alfredobejarano.productlisting.adapter.PostsAdapter
import me.alfredobejarano.productlisting.utilities.Injector
import me.alfredobejarano.productlisting.viewmodel.PostViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class PostsFragment : Fragment() {
    @Inject
    lateinit var vmFactory: PostViewModel.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = RecyclerView(requireContext()).also {
        it.id = R.id.postsList
        it.layoutManager = LinearLayoutManager(requireContext())
        it.adapter = PostsAdapter(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Injector.component.inject(this)
        val vm = ViewModelProviders.of(this, vmFactory)[PostViewModel::class.java]
        vm.postList.observe(this, Observer {
            if (it?.isNotEmpty() == true) {
                ((view as RecyclerView).adapter as PostsAdapter).setPosts(it)
            } else {
                Snackbar.make(view, R.string.no_posts_found, Snackbar.LENGTH_SHORT).show()
            }
        })
        vm.getPostsList()
    }
}
