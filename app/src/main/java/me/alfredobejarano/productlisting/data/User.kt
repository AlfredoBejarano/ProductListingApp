package me.alfredobejarano.productlisting.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Documentation goes here.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 01:30 AM
 * @version 1.0
 **/
data class User(
    @Expose
    @SerializedName("id_user")
    val id: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("image")
    val image: String,
    @Expose
    @SerializedName("last_name")
    val surName: String,
    @Expose
    @SerializedName("second_last_name")
    val lastName: String
)