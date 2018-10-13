package me.alfredobejarano.productlisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
     * @param username The username value for the login request.
     * @param password The password value for the login request.
     */
    fun requestLogin(username: String, password: String) = runOnIOThread {
        repo.performLoginRequest(LoginRequest(username, password))
    }
}