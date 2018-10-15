package me.alfredobejarano.productlisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.productlisting.data.Session
import me.alfredobejarano.productlisting.data.SessionRepository
import javax.inject.Inject

/**
 *
 * Simple [ViewModel] subclass that provides observation
 * for the UI controllers.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 10:42 PM
 * @version 1.0
 **/
class SessionViewModel
@Inject constructor(repo: SessionRepository) : ViewModel() {
    /**
     * [LiveData] reference that provides observation to the UI for the session.
     */
    var session: LiveData<List<Session>> =
        repo.retrieveSession()

    /**
     * Simple [ViewModelProvider.NewInstanceFactory] subclass
     * that provides custom constructor initialization for
     * a [SessionViewModel] class.
     */
    class Factory
    @Inject constructor(private val repo: SessionRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
            return SessionViewModel(repo) as T
        }
    }
}