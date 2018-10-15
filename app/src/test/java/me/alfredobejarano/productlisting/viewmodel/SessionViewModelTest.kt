package me.alfredobejarano.productlisting.viewmodel

import me.alfredobejarano.productlisting.data.SessionRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test case for the [SessionViewModel] class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 14/10/2018 - 09:27 PM
 */
@RunWith(MockitoJUnitRunner::class)
class SessionViewModelTest {
    @Mock
    lateinit var repository: SessionRepository

    /**
     * Test case that asserts when creating a
     * [SessionViewModel] class, the current
     * session gets fetched.
     */
    @Test
    fun createViewModel_fetchSessionTest() {
        // Create a new session ViewModel.
        SessionViewModel(repository)
        // Verify that the necessary function from the repository class gets called.
        Mockito.verify(repository)
            .retrieveSession()
    }
}