package me.alfredobejarano.productlisting.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

/**
 *
 * Repository class for the login related object and entities.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 08:27 PM
 * @version 1.0
 **/
class LoginRepository @Inject constructor(private val webservice: Webservice) {
    /**
     * Callback that responds to a LoginRequest.
     */
    private val loginCallback = object : Callback<Wrapper<Login>> {
        /**
         * Function executed when the result response with a 2XX HTTP code.
         */
        override fun onResponse(c: Call<Wrapper<Login>>, r: Response<Wrapper<Login>>) = if (r.isSuccessful)
            storeSession(r) // Store the session if the response was successful.
        else
            onFailure(c, HttpException(r)) // Report a failure if the response was not successful.

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

    /**
     * Stores a session from a response.
     * @param response The valid response object from the server.
     */
    private fun storeSession(response: Response<Wrapper<Login>>) {
        val wrapper = response.body()
        if (wrapper?.success == 1 && wrapper.data != null) {
            val session = Session(wrapper.data.accessToken, 0L)
        }
    }
}