package me.alfredobejarano.productlisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.productlisting.data.LoginRepository
import me.alfredobejarano.productlisting.data.LoginRequest
import me.alfredobejarano.productlisting.utilities.runOnIOThread
import javax.inject.Inject

/**
 *
 * Simple [ViewModel] subclass that provides observation
 * for the UI controllers about login operations.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 10:59 PM
 * @version 1.0
 **/
class LoginViewModel
@Inject constructor(private val repo: LoginRepository) : ViewModel() {
    /**
     * Reference to the response from a login request.
     */
    val loginRespose: LiveData<Boolean> = repo.result

    /**
     * Builds a [LoginRequest] object and performs a login request
     * to the repository class.
     * @param request Model class containing the login details.
     */
    fun requestLogin(request: LoginRequest) = runOnIOThread {
        repo.performLoginRequest(request)
    }

    /**
     * Simple [ViewModelProvider.NewInstanceFactory] subclass
     * that provides custom constructor initialization for
     * a [LoginViewModel] class.
     */
    class Factory
    @Inject constructor(private val repo: LoginRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
            return LoginViewModel(repo) as T
        }
    }
}