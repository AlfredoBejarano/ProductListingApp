package me.alfredobejarano.productlisting.data

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

/**
 *
 * Repository class for the login objects.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 08:27 PM
 * @version 1.0
 **/
class LoginRepository @Inject constructor(
    private val webservice: Webservice,
    private val repo: SessionRepository
) {
    /**
     * LiveData object that provides observation for the login process.
     */
    val result: MutableLiveData<Boolean> = repo.sessionFetched
    /**
     * Callback that responds to a LoginRequest.
     */
    private val loginCallback = object : Callback<Wrapper<Login>> {
        /**
         * Function executed when the result response with a 2XX HTTP code.
         */
        override fun onResponse(c: Call<Wrapper<Login>>, r: Response<Wrapper<Login>>): Unit = r.body()?.data?.let {
            repo.fetchUserFromToken(it.accessToken)
        }?.run {
            onFailure(c, HttpException(r))
        } ?: Unit

        /**
         * Function that gets called when the web call doesn't respond with a 2xx HTTP code.
         */
        override fun onFailure(call: Call<Wrapper<Login>>, t: Throwable) = Unit
    }

    /**
     * Performs a request to retrieve the session data ofa login request.
     * @param request The request object containing the login data.
     */
    fun performLoginRequest(request: LoginRequest) =
        webservice.performLoginRequest(request).enqueue(loginCallback)
}