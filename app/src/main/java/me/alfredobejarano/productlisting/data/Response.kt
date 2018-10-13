package me.alfredobejarano.productlisting.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Model class that wraps common properties from the API response.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 07:44 PM
 * @version 1.0
 **/
data class Response<T>(
    @Expose
    @SerializedName("success")
    val success: Int,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("data")
    val data: T?
)