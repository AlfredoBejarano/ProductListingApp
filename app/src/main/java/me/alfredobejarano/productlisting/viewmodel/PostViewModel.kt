package me.alfredobejarano.productlisting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.productlisting.data.PostRepository
import javax.inject.Inject

/**
 *
 * Simple [ViewModel] subclass that process UI request
 * for [Post] operations.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 06:57 PM
 * @version 1.0
 **/
class PostViewModel
@Inject constructor(private val repo: PostRepository) {
    /**
     * Property that allows observation for a list of posts.
     */
    val postList = repo.results

    /**
     * Retrieves a list of posts from the repository.
     */
    fun getPostsList() = repo.fetchPosts()

    /**
     * Fetches a cached post from the database.
     */
    fun getPost(id: Int) = repo.fetchPost(id)

    /**
     * Simple [ViewModelProvider.NewInstanceFactory] subclass
     * that provides custom constructor initialization for
     * a [PostViewModel] class.
     */
    class Factory
    @Inject constructor(private val repo: PostRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
            return PostViewModel(repo) as T
        }
    }
}