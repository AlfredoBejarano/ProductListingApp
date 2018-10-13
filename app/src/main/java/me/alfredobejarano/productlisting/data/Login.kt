package me.alfredobejarano.productlisting.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Model class that contains the results of a successful
 * login request.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 07:48 PM
 * @version 1.0
 **/
data class Login(
    @Expose
    @SerializedName("token_type")
    val type: String,
    @Expose
    @SerializedName("expires_in")
    val expirationTime: Int,
    @Expose
    @SerializedName("access_token")
    val accessToken: String,
    @Expose
    @SerializedName("refresh_token")
    val refreshToken: String
)