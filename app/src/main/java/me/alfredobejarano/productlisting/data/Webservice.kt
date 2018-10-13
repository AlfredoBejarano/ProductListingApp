package me.alfredobejarano.productlisting.data

import me.alfredobejarano.productlisting.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 *
 * Interface that defines methods for accessing the backend.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 08:17 PM
 * @version 1.0
 **/
interface Webservice {
    /**
     * API definition for performing a login request that
     * retrieves the access token info for the login.
     */
    @GET("login")
    @Headers(BuildConfig.HeaderLang, BuildConfig.HeaderAppVersion)
    fun performLoginRequest(@Body request: LoginRequest): Call<Wrapper<Login>>
}