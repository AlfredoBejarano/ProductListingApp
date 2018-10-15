package me.alfredobejarano.productlisting.viewmodel

import me.alfredobejarano.productlisting.data.PostRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test class for the [PostViewModel] class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 14/10/2018 - 09:14 PM
 */
@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {
    /**
     * Mocked [PostRepository] object.
     */
    @Mock
    lateinit var repository: PostRepository

    /**
     * Tests that a list of posts gets called.
     */
    @Test
    fun getPostsList() {
        // Reference the ViewModel to perform test on it.
        val viewModel = PostViewModel(repository)
        // Fetch a lists of posts.
        viewModel.getPostsList()
        // Assert that the necessary function for the mock object gets called.
        Mockito.verify(repository).fetchPosts()
    }

    /**
     * Test that fetching a post gets
     * called with a given Id value (0).
     */
    @Test
    fun getPost() {
        // Reference the ViewModel to perform test on it.
        val viewModel = PostViewModel(repository)
        // Fetch a post from the repository by a given id.
        viewModel.getPost(0)
        // Assert that the necessary function for the mock object gets called with the given id.
        Mockito.verify(repository).fetchPost(0)
    }
}