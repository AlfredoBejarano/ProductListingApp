package me.alfredobejarano.productlisting.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Data class that serves as the model for the
 * list of post functionality, this class defines
 * the JSON structure of a request for getting all
 * the posts from the backend.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 01:11 AM
 * @version 1.0
 **/
class PostsList(
    @Expose
    @SerializedName("to")
    val to: Int,
    @Expose
    @SerializedName("from")
    val from: Int,
    @Expose
    @SerializedName("total")
    val total: Int,
    @Expose
    @SerializedName("per_page")
    val perPage: Int,
    @Expose
    @SerializedName("path")
    val path: String,
    @Expose
    @SerializedName("current_page")
    val currentPage: Int,
    @Expose
    @SerializedName("last_page")
    val lastPage: String,
    @Expose
    @SerializedName("last_page_url")
    val lastPageURL: String,
    @Expose
    @SerializedName("next_page_url")
    val nextPageURL: String,
    @Expose
    @SerializedName("list_post", alternate = ["data"])
    val results: List<Post>,
    @Expose
    @SerializedName("ﬁrst_page_url")
    val firstPageURL: String,
    @Expose
    @SerializedName("prev_page_url")
    val previousPageURL: String
)