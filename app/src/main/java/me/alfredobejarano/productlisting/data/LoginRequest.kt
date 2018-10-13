package me.alfredobejarano.productlisting.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Model class that defines the values for performing a login request.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 07:40 PM
 * @version 1.0
 **/
data class LoginRequest(
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("password")
    val password: String
)