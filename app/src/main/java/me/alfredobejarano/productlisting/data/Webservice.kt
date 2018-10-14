package me.alfredobejarano.productlisting.data

import me.alfredobejarano.productlisting.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    /**
     * Function that defines how to retrieve a user profile using
     * an access token.
     */
    @GET("user/profile")
    @Headers(BuildConfig.HeaderLang, BuildConfig.HeaderAppVersion)
    fun fetchUserProfile(@Header("Authorization") accessToken: String): Call<Wrapper<User>>

    /**
     * Fetches a list of posts from the cloud.
     */
    @GET("post/all")
    @Headers(BuildConfig.HeaderLang, BuildConfig.HeaderAppVersion)
    fun fetchPosts(): Call<Wrapper<PostsList>>
}