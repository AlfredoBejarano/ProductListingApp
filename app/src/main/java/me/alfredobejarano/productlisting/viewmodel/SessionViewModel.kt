package me.alfredobejarano.productlisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
}